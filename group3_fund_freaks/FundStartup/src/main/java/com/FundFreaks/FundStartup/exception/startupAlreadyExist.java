package com.FundFreaks.FundStartup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code= HttpStatus.CONFLICT,reason="With this id idea is present")
public class startupAlreadyExist extends Exception {

}
