package com.cardinalhealth.ids.boss.deidentify.job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cardinalhealth.framework.constant.CommonConstants;
import com.cardinalhealth.framework.exception.SystemFault;
import com.cardinalhealth.ids.boss.deidentify.job.constants.DeIdentificationJobConstants;
import com.cardinalhealth.ids.boss.deidentify.job.service.IDeIdentifyService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * This class contains the end point to de-identify data.<br>
 */
@Api
@RestController
@RequestMapping(DeIdentificationJobConstants.ApiPaths.SERVICE_BASE)
@Tag(name = DeIdentificationJobConstants.OpenApiTags.DEIDENTIFY_CONTROLLER_TAG)
public class DeIdentifyController {

	@Autowired
	private IDeIdentifyService payableJobService;

	/**
	 * End point to get PDInvoiceHeaderModel List.<br>
	 * 
	 * @param
	 * @return 
	 */
	@Operation(summary = DeIdentificationJobConstants.ApiPaths.DeIdentifyJobApiPaths.DEIDENTIFY_DESC, responses = {
			@ApiResponse(responseCode = CommonConstants.ApiResponseCodes.HTTP_OK, description = DeIdentificationJobConstants.ApiResponseMessages.DeidentifyResponseMessages.DEIDENTIFY_SUCCESS_MSG),
			@ApiResponse(responseCode = CommonConstants.ApiResponseCodes.HTTP_INTERNAL_SERVER_ERROR, description = DeIdentificationJobConstants.ApiResponseMessages.SYSTEM_ERROR, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SystemFault.class))) })
	@GetMapping(path = DeIdentificationJobConstants.ApiPaths.DeIdentifyJobApiPaths.DEIDENTIFY_PATH, produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deidentifyData() {

		payableJobService.deidentifyData();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
