package com.assignment.store.advice;

import com.assignment.store.exception.InvalidTradeVersionException;
import com.assignment.store.exception.MaturityDateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StoreAdviceControllerTest {

    @InjectMocks
    private StoreAdviceController storeAdviceController;

    @Test
    public void testHandleInvalidTradeVersionException() {
        InvalidTradeVersionException exception = new InvalidTradeVersionException("Lower version of Trade");
        ResponseEntity<Object> response = storeAdviceController.handleInvalidTradeVersionException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Lower version of Trade", response.getBody());
    }

    @Test
    public void testHandleMaturityDateException() {
        MaturityDateException exception = new MaturityDateException("Maturity date is in past");
        ResponseEntity<Object> response = storeAdviceController.handleMaturityDateException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Maturity date is in past", response.getBody());
    }
}
