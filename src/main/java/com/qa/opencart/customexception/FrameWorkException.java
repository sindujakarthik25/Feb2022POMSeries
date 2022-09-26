package com.qa.opencart.customexception;

public class FrameWorkException extends RuntimeException {

	public FrameWorkException(String messg){
		super(messg);
		printStackTrace();

}
}
