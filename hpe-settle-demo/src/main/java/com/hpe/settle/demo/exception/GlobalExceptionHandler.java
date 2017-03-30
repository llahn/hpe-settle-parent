package com.hpe.settle.demo.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义异常处理 即在Controller中抛出自定义的异常时，客户端收到更友好的JSON格式的提示。而不是常见的报错页面。
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	/*
	 * @ExceptionHandler(value =
	 * Exception.class)表示让Spring捕获到所有抛出的Exception异常，并交由这个被注解的方法处理。
	 */
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("error");
		return mav;
	}

	/*
	 * @ResponseBody表示返回的对象，Spring会自动把该对象进行json转化，最后写入到Response中。
	 */
	@ExceptionHandler(value = HpeSettleException.class)
	@ResponseBody
	public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, HpeSettleException e) throws Exception {
		ErrorInfo<String> r = new ErrorInfo<>();
		r.setMessage(e.getMessage());
		r.setCode(ErrorInfo.ERROR);
		r.setData("Some Data");
		r.setUrl(req.getRequestURL().toString());
		return r;
	}
}
