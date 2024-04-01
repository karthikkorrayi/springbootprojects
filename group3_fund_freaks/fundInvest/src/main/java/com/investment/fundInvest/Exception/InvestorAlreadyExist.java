package com.investment.fundInvest.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,   reason="With this id investor is present")
public class InvestorAlreadyExist extends Exception{
}
