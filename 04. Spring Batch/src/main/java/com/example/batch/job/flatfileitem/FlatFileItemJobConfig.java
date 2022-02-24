package com.example.batch.job.flatfileitem;

import com.example.batch.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/**
 * FlatFileItemJob
 * - CSV file과 같은 text file을 chunk 방식으로 처리하는 job
 *
 * reference
 * - https://sky-h-kim.tistory.com/38
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class FlatFileItemJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final int CHUNK_SIZE = 3;

    @Bean
    public Job flatFileItemJob() {
        return jobBuilderFactory.get("flatFileItemJob")
            .start(flatFileItemStep())
            .build();
    }

    @Bean
    public Step flatFileItemStep() {
        return stepBuilderFactory.get("flatFileItemStep")
            .<ItemDto, ItemDto>chunk(CHUNK_SIZE)
            .reader(flatFileItemReader())
            .writer(flatFileItemWriter())
            .build();
    }

    /**
     * resource : file 경로
     * linesToSkip : skip 할 line (header 정보)
     * delimited : 구분자 (default : ',')
     * names : object 할당 할 필드 이름 (DTO 설정 된 필드명)
     * targetType : return type
     */
    @Bean
    @StepScope
    public FlatFileItemReader<ItemDto> flatFileItemReader() {
        String filePath = "/Users/henry.yoon/Documents/private/spring/04. Spring Batch/src/main/resources/csv/";
        String fileName = "inputSample01.csv";

        return new FlatFileItemReaderBuilder<ItemDto>()
            .name("flatFileItemReader")
            .resource(new FileSystemResource(filePath + fileName))
            .linesToSkip(1)
            .delimited().delimiter(",")
            .names("name", "age")
            .targetType(ItemDto.class)
//            .recordSeparatorPolicy(new SimpleRecordSeparatorPolicy() {
//                @Override
//                public String postProcess(String record) {
//                    return record.trim();
//                }
//            })
            .build();
    }

    /**
     * append : 해당 파일에 중복으로 write 요청 시 데이터 append 활성화 여부
     * - true : 기존 작성된 데이터에 이어서 추가
     * - read : 기존 작성된 데이터 날리고 새로 추가
     */
    @Bean
    @StepScope
    public FlatFileItemWriter<ItemDto> flatFileItemWriter() {
        String filePath = "/Users/henry.yoon/Documents/private/spring/04. Spring Batch/src/main/resources/csv/";
        String fileName = "outputSample01.csv";

        return new FlatFileItemWriterBuilder<ItemDto>()
            .name("flatFileItemWriter")
            .resource(new FileSystemResource(filePath + fileName))
            .append(true)
            .delimited()
            .names("name", "age")
            .build();
    }

    /**
     * CHUNK_SIZE 만큼 쌓은 후 출력한다. (출력되는 구분선 확인)
     */
    private ItemWriter<ItemDto> printWriter() {
        return items -> {
            for (ItemDto item : items) {
                log.info("Current item = {}", item);
            }
            log.info("=======================");
        };
    }
}
