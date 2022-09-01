/**
 * 
 */
package trio.auction.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author sonia.guama
 *
 */
public class BidderDto implements Serializable {

	private static final long serialVersionUID = 3176525803663157475L;

	@NotBlank
	@NotEmpty
	private String bidderName;
	private BigDecimal startBid;
	private BigDecimal maxBid;
	private BigDecimal autoIncrementAmount;

	public BidderDto(String bidderName, BigDecimal startBid, BigDecimal maxBid, BigDecimal autoIncrementAmount) {
		this.bidderName = bidderName;
		this.startBid = startBid;
		this.maxBid = maxBid;
		this.autoIncrementAmount = autoIncrementAmount;
	}

	public BidderDto() {
	}

	public String getBidderName() {
		return bidderName;
	}

	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}

	public BigDecimal getStartBid() {
		return startBid;
	}

	public void setStartBid(BigDecimal startBid) {
		this.startBid = startBid;
	}

	public BigDecimal getMaxBid() {
		return maxBid;
	}

	public void setMaxBid(BigDecimal maxBid) {
		this.maxBid = maxBid;
	}

	public BigDecimal getAutoIncrementAmount() {
		return autoIncrementAmount;
	}

	public void setAutoIncrementAmount(BigDecimal autoIncrementAmount) {
		this.autoIncrementAmount = autoIncrementAmount;
	}

}
