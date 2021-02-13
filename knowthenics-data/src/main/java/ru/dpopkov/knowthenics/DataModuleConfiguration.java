package ru.dpopkov.knowthenics;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This configuration added in order to make possible isolated Integration Tests in the module 'data'.
 */
@Configuration
@ComponentScan
public class DataModuleConfiguration {
}
