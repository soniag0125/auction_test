package trio.auction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import trio.auction.dto.BidderDto;
import trio.auction.dto.WinnerAuctionDto;
import trio.auction.services.AuctionService;

class AuctionServiceTest {

	@InjectMocks
	AuctionService auctionService;

	@BeforeEach
	void inicializer() {
		auctionService = new AuctionService();
	}

	@Test
	void shouldValidateStartBidWinner() {
		auctionService.setBidders(prepareStartBidWinner());
		WinnerAuctionDto result = auctionService.getWinner();
		assertEquals(prepareStartBidWinner().get(0).getBidderName(), result.getBidder().getBidderName());
	}

	@Test
	void shouldValidateSameBidAndWinTheFistBidder() {
		auctionService.setBidders(prepareSameBid());
		WinnerAuctionDto result = auctionService.getWinner();
		assertEquals(prepareStartBidWinner().get(1).getBidderName(), result.getBidder().getBidderName());
	}

	@Test
	void shouldValidateWinnerWithMaxAmount() {
		auctionService.setBidders(prepareBidWonFirstToReachMaxAmount());
		WinnerAuctionDto result = auctionService.getWinner();
		assertEquals(prepareStartBidWinner().get(2).getBidderName(), result.getBidder().getBidderName());
	}

	@Test
	void shouldValidateWinnerWithHigherBid() {
		auctionService.setBidders(prepareHigherBid());
		WinnerAuctionDto result = auctionService.getWinner();
		assertEquals(prepareStartBidWinner().get(0).getBidderName(), result.getBidder().getBidderName());
	}

	@Test
	void shouldValidateWinnerWithHigherBidAtFirstOffer() {
		auctionService.setBidders(prepareHigherBidAtFirstOffer());
		WinnerAuctionDto result = auctionService.getWinner();
		assertEquals(prepareStartBidWinner().get(1).getBidderName(), result.getBidder().getBidderName());
	}

	private List<BidderDto> prepareHigherBidAtFirstOffer() {
		List<BidderDto> bidders = new ArrayList<>();
		BidderDto bidder1 = new BidderDto("Ana", new BigDecimal(2500), new BigDecimal(3000), new BigDecimal(500));
		BidderDto bidder2 = new BidderDto("Diana", new BigDecimal(2800), new BigDecimal(3100), new BigDecimal(201));
		BidderDto bidder3 = new BidderDto("Ivana", new BigDecimal(2501), new BigDecimal(3200), new BigDecimal(247));
		bidders.add(bidder1);
		bidders.add(bidder2);
		bidders.add(bidder3);
		return bidders;
	}

	private List<BidderDto> prepareHigherBid() {
		List<BidderDto> bidders = new ArrayList<>();
		BidderDto bidder1 = new BidderDto("Ana", new BigDecimal(700), new BigDecimal(725), new BigDecimal(2));
		BidderDto bidder2 = new BidderDto("Diana", new BigDecimal(599), new BigDecimal(725), new BigDecimal(15));
		BidderDto bidder3 = new BidderDto("Ivana", new BigDecimal(625), new BigDecimal(725), new BigDecimal(8));
		bidders.add(bidder1);
		bidders.add(bidder2);
		bidders.add(bidder3);
		return bidders;
	}

	private List<BidderDto> prepareBidWonFirstToReachMaxAmount() {
		List<BidderDto> bidders = new ArrayList<>();
		BidderDto bidder1 = new BidderDto("Ana", new BigDecimal(50), new BigDecimal(80), new BigDecimal(3));
		BidderDto bidder2 = new BidderDto("Diana", new BigDecimal(60), new BigDecimal(82), new BigDecimal(2));
		BidderDto bidder3 = new BidderDto("Ivana", new BigDecimal(55), new BigDecimal(85), new BigDecimal(5));
		bidders.add(bidder1);
		bidders.add(bidder2);
		bidders.add(bidder3);
		return bidders;
	}

	private List<BidderDto> prepareStartBidWinner() {
		List<BidderDto> bidders = new ArrayList<>();
		BidderDto bidder1 = new BidderDto("Ana", new BigDecimal(100), new BigDecimal(150), new BigDecimal(25));
		BidderDto bidder2 = new BidderDto("Diana", new BigDecimal(85), new BigDecimal(95), new BigDecimal(5));
		BidderDto bidder3 = new BidderDto("Ivana", new BigDecimal(80), new BigDecimal(95), new BigDecimal(5));
		bidders.add(bidder1);
		bidders.add(bidder2);
		bidders.add(bidder3);
		return bidders;
	}

	private List<BidderDto> prepareSameBid() {
		List<BidderDto> bidders = new ArrayList<>();
		BidderDto bidder1 = new BidderDto("Diana", new BigDecimal(100), new BigDecimal(150), new BigDecimal(25));
		BidderDto bidder2 = new BidderDto("Ana", new BigDecimal(100), new BigDecimal(150), new BigDecimal(25));
		BidderDto bidder3 = new BidderDto("Ivana", new BigDecimal(100), new BigDecimal(150), new BigDecimal(25));
		bidders.add(bidder1);
		bidders.add(bidder2);
		bidders.add(bidder3);
		return bidders;
	}

}
