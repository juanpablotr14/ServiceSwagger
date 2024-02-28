package co.com.tascon.microservice.resolveEnigmaApi.api;

import co.com.tascon.microservice.resolveEnigmaApi.model.GetEnigmaRequest;
import co.com.tascon.microservice.resolveEnigmaApi.model.GetEnigmaStepResponse;
import co.com.tascon.microservice.resolveEnigmaApi.model.Header;
import co.com.tascon.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.tascon.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class GetStepApiController implements GetStepApi {

    public ResponseEntity<JsonApiBodyResponseSuccess> getStep(@Valid @RequestBody JsonApiBodyRequest body) {
    	
    	GetEnigmaRequest enigmaRequest = body.getData().get(0);
        Header header = enigmaRequest.getHeader();
        String id = header.getId();
        String type = header.getType();
        String enigma = enigmaRequest.getEnigma();

        String solution = answerEnigma(enigma);

        GetEnigmaStepResponse response = new GetEnigmaStepResponse();
        response.setId(id);
        response.setType(type);
        response.setSolution(solution);

        JsonApiBodyResponseSuccess responseBody = new JsonApiBodyResponseSuccess();
        responseBody.addDataItem(response);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    private String answerEnigma(String enigmaQuestion) {
        return "Step1: Open the regrigerator -  Step2: Put the giraffe in - Step3: Close de door.";
    }
}
