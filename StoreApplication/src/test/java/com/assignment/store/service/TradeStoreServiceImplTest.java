package com.assignment.store.service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.assignment.store.exception.InvalidTradeVersionException;
import com.assignment.store.exception.MaturityDateException;
import com.assignment.store.model.TradeStore;
import com.assignment.store.repository.TradeStoreRepository;



@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TradeStoreServiceImplTest {
	
	@Mock
    private TradeStoreRepository tradeStoreRepository;
	
	@InjectMocks
    private TradeStoreServiceImpl tradeStoreService;
	
	@Test
	void checkTradeVersionTest() {
		boolean value = tradeStoreService.checkTradeVersion(getTrade("T1",1,LocalDate.now()), getTrade("T1", 2, LocalDate.now()));
		Assertions.assertEquals(false, value);
	}
	
	@Test
	void checkTradeMaturityDateTest() {
		boolean value = tradeStoreService.checkTradeMaturity(getTrade("T1", 1, LocalDate.now()));
		Assertions.assertEquals(true, value);
	}
    
	@Test
    void testCheckTradeVersionWithHigherVersion() {
        TradeStore tradeStore = getTrade("T1", 2, LocalDate.now());
        TradeStore existingTrade = getTrade("T1", 1, LocalDate.now());

        boolean result = tradeStoreService.checkTradeVersion(tradeStore, existingTrade);

        Assertions.assertTrue(result, "Trade version accepted for higher version");
    }

	@Test
    void testCheckTradeVersionWithLowerVersion() {
        TradeStore tradeStore = getTrade("T1", 1, LocalDate.now());
        TradeStore existingTrade = getTrade("T1", 2, LocalDate.now());

        boolean result = tradeStoreService.checkTradeVersion(tradeStore, existingTrade);

        Assertions.assertFalse(result, "Trade version rejected for a lower version");
    }

	@Test
    void testCheckTradeMaturityWithFutureDate() {
        TradeStore tradeStore = getTrade("T1", 1, LocalDate.now().plusDays(1));

        boolean result = tradeStoreService.checkTradeMaturity(tradeStore);

        Assertions.assertTrue(result, "Trade accepted for future maturity date");
    }

	@Test
    void testCheckTradeMaturityWithPastDate() {
        TradeStore tradeStore = getTrade("T1", 1, LocalDate.now().minusDays(1));

        boolean result = tradeStoreService.checkTradeMaturity(tradeStore);

        Assertions.assertFalse(result, "Given Trade rejected for past maturity date");
    }

	@Test
    void testSaveTradeWithNewTrade() {
        TradeStore tradeStore = getTrade("T1", 1, LocalDate.now().plusDays(1));
        when(tradeStoreRepository.findById(tradeStore.getTradeId())).thenReturn(Optional.empty());
        when(tradeStoreRepository.save(tradeStore)).thenReturn(tradeStore);

        TradeStore savedTrade = tradeStoreService.saveTrade(tradeStore);

        Assertions.assertNotNull(savedTrade, "New trade should be saved");
        Assertions.assertEquals(LocalDate.now(), savedTrade.getCreatedDate(), "Created date should be set");
    }

    @Test
    void testSaveTradeWithExistingTradeAndHigherVersion() {
        TradeStore tradeStore = getTrade("T1", 2, LocalDate.now().plusDays(1));
        TradeStore existingTrade = getTrade("T1", 1, LocalDate.now().plusDays(1));
        when(tradeStoreRepository.findById(tradeStore.getTradeId())).thenReturn(Optional.of(existingTrade));
        when(tradeStoreRepository.save(tradeStore)).thenReturn(tradeStore);

        TradeStore savedTrade = tradeStoreService.saveTrade(tradeStore);

        Assertions.assertNotNull(savedTrade, "Trade with higher version should be saved");
        Assertions.assertEquals(LocalDate.now(), savedTrade.getCreatedDate(), "Created date should be set");
    }

    @Test
    void testSaveTradeWithExistingTradeAndLowerVersion() {
        TradeStore tradeStore = getTrade("T1", 1, LocalDate.now().plusDays(1));
        TradeStore existingTrade = getTrade("T1", 2, LocalDate.now().plusDays(1));
        when(tradeStoreRepository.findById(tradeStore.getTradeId())).thenReturn(Optional.of(existingTrade));
        Assertions.assertThrows(InvalidTradeVersionException.class, () -> tradeStoreService.saveTrade(tradeStore),
                "Lower version trade should be rejected");
    }

    @Test
    void testSaveTradeWithPastMaturityDate() {
        TradeStore tradeStore = getTrade("T1", 1, LocalDate.now().minusDays(1));

        Assertions.assertThrows(MaturityDateException.class, () -> tradeStoreService.saveTrade(tradeStore),
                "Trade with past maturity date should be rejected");
    }

	
	public TradeStore getTrade(String tradeId, int version,LocalDate  maturityDate){
		TradeStore trade = new TradeStore();
		trade.setTradeId(tradeId);
		trade.setBookId("B1");
		trade.setVersion(version);
		trade.setCounterPartyId("CP-1");
		trade.setMaturityDate(maturityDate);
		trade.setExpiredFlag("Y");
		return trade;
	}
}