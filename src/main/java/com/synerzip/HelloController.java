package com.synerzip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.synerzip.util.SMSUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;


@RestController
@Api(basePath = "/apis/registration", value = "User", description = "Masterdata APIs", produces = "application/json",position = 1)
@RequestMapping(value = "/apis/registration", produces = MediaType.APPLICATION_JSON_VALUE)
public class HelloController {

	@Autowired
	private SMSUtil util;
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json",value="/registeruser/{mobilenumber}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Security Questions", notes = "Fetch Security questions")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "No Security Questions were found"),
		 })
    public String getSecurityQuestions(@PathVariable String mobilenumber) {
      
    	String code = util.sendActivationCodeSMS(mobilenumber);
        return code;
    } 
}
