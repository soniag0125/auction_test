/**
 * 
 */
package trio.auction.dto;

import java.math.BigDecimal;

/**
 * @author sonia.guama
 *
 */
public class BidderAuctionDto {
	
	private BidderDto bidder;
	private Integer maxCounter;
	private BigDecimal maxBidAmount;
	
	
	
	
	public BidderAuctionDto(BidderDto bidder, Integer maxCounter, BigDecimal maxBidAmount) {
		this.bidder = bidder;
		this.maxCounter = maxCounter;
		this.maxBidAmount = maxBidAmount;
	}
	
	
	public BidderAuctionDto() {
	}


	public BidderDto getBidder() {
		return bidder;
	}
	public void setBidder(BidderDto bidder) {
		this.bidder = bidder;
	}
	public Integer getMaxCounter() {
		return maxCounter;
	}
	public void setMaxCounter(Integer maxCounter) {
		this.maxCounter = maxCounter;
	}
	public BigDecimal getMaxBidAmount() {
		return maxBidAmount;
	}
	public void setMaxBidAmount(BigDecimal maxBidAmount) {
		this.maxBidAmount = maxBidAmount;
	}
	
	
	
	
	

}
