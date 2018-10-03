package com.homedirect.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.homedirect.constant.NotificationListener;
import com.homedirect.entity.Account;
import com.homedirect.processor.impl.AccountItemProcessorImpl;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
   @Autowired
   public JobBuilderFactory jobBuilderFactory;

   @Autowired
   public StepBuilderFactory stepBuilderFactory;

   @Autowired
   public DataSource dataSource;

   @Bean
   public FlatFileItemReader<Account> reader() {
      FlatFileItemReader<Account> reader = new FlatFileItemReader<Account>();
      reader.setResource(new ClassPathResource("file.csv"));
      reader.setLineMapper(new DefaultLineMapper<Account>() {
         {
            setLineTokenizer(new DelimitedLineTokenizer() {
               {
                  setNames(new String[] { "accountNumber", "username", "password", "amount" });
               }
            });
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Account>() {
               {
                  setTargetType(Account.class);
               }
            });
         }
      });
      return reader;
   }
   @Bean
   public AccountItemProcessorImpl processor() {
      return new AccountItemProcessorImpl();
   }
   @Bean
   public JdbcBatchItemWriter<Account> writer() {
      JdbcBatchItemWriter<Account> writer = new JdbcBatchItemWriter<Account>();
      writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Account>());
      writer.setSql("INSERT INTO USERS (accountNumber, username, password, amount) VALUES (:accountNumber, :username, :password, :amount)");
      writer.setDataSource(dataSource);
      return writer;
   }
   @Bean
   public Job importUserJob(NotificationListener listener) {
      return jobBuilderFactory.get("importUserJob").incrementer(
         new RunIdIncrementer()).listener(listener).flow(step1()).end().build();
   }
   @Bean
   public Step step1() {
      return stepBuilderFactory.get("step1").<Account, Account>chunk(10).reader(reader()).processor(processor()).writer(writer()).build();
   }
}
