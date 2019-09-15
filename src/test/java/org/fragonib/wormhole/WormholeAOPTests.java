package org.fragonib.wormhole;

import org.fragonib.wormhole.greeting.GreetingController;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WormholeAOPTests {

	@Autowired
	private GreetingController controller;

	@Test
	public void given() {

		// When: Simulate incoming request
		String greeting = controller.greets();

		// Then: response includes username provided via wormhole
		assertThat(greeting).isEqualTo("Hello Fran");
	}

}
