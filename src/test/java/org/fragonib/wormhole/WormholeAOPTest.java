package org.fragonib.wormhole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.fragonib.wormhole.greeting.GreetingController;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WormholeAOPTest {

    @Autowired
    private GreetingController controller;

    @Test
    public void given() {

        // When: Simulate incoming request
        final String greeting = this.controller.greets();

        // Then: Response includes username provided via wormhole
        assertThat(greeting).isEqualTo("Hello Fran");

    }

}
