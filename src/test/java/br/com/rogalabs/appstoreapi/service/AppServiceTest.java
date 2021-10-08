package br.com.rogalabs.appstoreapi.service;

import br.com.rogalabs.appstoreapi.AbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Anderson on 06/10/2021
 * @project app-store-api
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AppServiceTest extends AbstractTest {

    @Autowired
    private AppService appService;

    @BeforeEach
    void setUp() {
    }

}