package com.meterCostFileImporter.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.meterCostFileImporter.controller.LocationItemProcessor;
import com.meterCostFileImporter.entity.Location;
import com.meterCostFileImporter.listener.JobCompletionNotificationListener;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Location> reader() {
        return new FlatFileItemReaderBuilder<Location>()
            .name("locationItemReader")
            .resource(new ClassPathResource("data.csv"))
            .delimited()
            .names(new String[]{"country", "state", "city", "neighborhood", "cost"})
            .fieldSetMapper(new BeanWrapperFieldSetMapper<Location>() {{
                setTargetType(Location.class);
            }})
            .build();
    }

    @Bean
    public LocationItemProcessor processor() {
        return new LocationItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Location> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Location>()
            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .sql("INSERT INTO location (country, state, city, neighborhood, cost) VALUES (:country, :state, :city, :neighborhood, :cost)")
            .dataSource(dataSource)
            .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("meterCostFileImporterJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .end()
            .build();
    }

    @Bean
    public Step step1( JdbcBatchItemWriter<Location> writer) {
        return stepBuilderFactory.get("step1")
            .<Location, Location> chunk(2)
            .reader(reader())
            .processor(processor())
            .writer(writer)
            .build();
    }
}