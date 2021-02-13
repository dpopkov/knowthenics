package ru.dpopkov.knowthenics;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * This configuration added in order to make possible isolated Integration Tests in the module 'data'.
 */
@SpringBootConfiguration
@EnableAutoConfiguration
public class DataModuleTestConfiguration extends DataModuleConfiguration {
}
