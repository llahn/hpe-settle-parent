package com.hpe.settle.demo.config;

import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.hpe.settle.demo.enums.Events;
import com.hpe.settle.demo.enums.States;

/**
 * @EnableStateMachine 状态机机制，表示启动状态机机制
 *
 * 总结 
 * Spring StateMachine 让状态机结构更加层次化，可以帮助开发者简化状态机的开发过程。
 * 我们来回顾下几个核心步骤
 * 		定义状态枚举。
 * 		定义事件枚举。
 * 		定义状态机配置，设置初始状态，以及状态与事件之间的关系。
 * 		定义状态监听器，当状态变更时，触发方法。
 */
@Configuration
@EnableStateMachine 
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* 
	 * 初始化状态机状态
	 */
	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
		states.withStates()
			.initial(States.UNPAID)  //定义初始化状态
			.states(EnumSet.allOf(States.class)); // 定义状态机状态
	}

	/* 
	 * 初始化状态迁移事件
	 * 其中 source 指定原始状态，target 指定目标状态，event 指定触发事件。
	 */
	@Override
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
		transitions
		// 支付事件
		// 待支付->待收货
		.withExternal()
			.source(States.UNPAID)  // source - 当前状态
			.target(States.WAITING_FOR_RECEIVE) // target - 目标状态
			.event(Events.PAY).and() // event - 触发事件
		// 收货事件
		// 待收货 -> 结束
		.withExternal()
			.source(States.WAITING_FOR_RECEIVE)
			.target(States.DONE)
			.event(Events.RECEIVE);
		
	}
}
