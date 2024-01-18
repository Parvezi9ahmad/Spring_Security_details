package in.ashokit.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import in.ashokit.entity.Customer;
import in.ashokit.repo.CustomerRepository;

@Configuration
@EnableBatchProcessing
public class CsvBatchConfig {
	@Autowired
	private CustomerRepository customerRepo;
	
	/*
	 * @Autowired private StepBuilderFactory stepBuilderFactory;
	 */
	@Autowired(required=true)
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private JobBuilderFactory jobBuilderfactory;

	//create reader
	@Bean
	public FlatFileItemReader<Customer> customerReader(){
		FlatFileItemReader<Customer> itemReader=new FlatFileItemReader<>();
		itemReader.setResource(new FileSystemResource("src/main/resources/customers.csv"));
		itemReader.setName("csv-reader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		return itemReader;
	}

	private LineMapper<Customer> lineMapper() {
		DefaultLineMapper<Customer> lineMapper=new DefaultLineMapper<>();
		
		DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer(); 
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("id","firstName","lastName","email","gender","contactNo","country","dob");
		
		BeanWrapperFieldSetMapper<Customer> fieldSetMapper=new BeanWrapperFieldSetMapper();
		fieldSetMapper.setTargetType(Customer.class);
		
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}
	
	//create processor
	@Bean
	public CustomerProcessor customerProcessor() {
		return new CustomerProcessor();
	}
	
	//create writer
	@Bean
	public RepositoryItemWriter <Customer> customerWriter(){
		RepositoryItemWriter<Customer> repositoryWriter=new RepositoryItemWriter<>();
		repositoryWriter.setRepository(customerRepo);
		repositoryWriter.setMethodName("save");
		
		return repositoryWriter;
	}
	
	//create step
	@Bean
	public Step step1() {
		/*
		 * return stepBuilderFactory.get("Step-1").<Customer,Customer>chunk(10)
		 * .reader(customerReader()) .processor(customerProcessor())
		 * .writer(customerWriter()) .build();
		 */
		return stepBuilderFactory.get("step-1").<Customer, Customer>chunk(10)
				  .reader(customerReader())
				  .processor(customerProcessor())
				  .writer(customerWriter())
				  .build();
	}
	
	@Bean
	public Job job() {
		return jobBuilderfactory.get("Customers-job")
				.flow(step1())
				.end()
				.build();
	}
	
}
