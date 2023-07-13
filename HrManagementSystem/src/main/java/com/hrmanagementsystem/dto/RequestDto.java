package com.hrmanagementsystem.dto;

import java.util.Objects;

public class RequestDto {
	private Integer rating;
	private Boolean promoted;
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public Boolean getPromoted() {
		return promoted;
	}
	public void setPromoted(Boolean promoted) {
		this.promoted = promoted;
	}
	@Override
	public int hashCode() {
		return Objects.hash(promoted, rating);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestDto other = (RequestDto) obj;
		return Objects.equals(promoted, other.promoted) && Objects.equals(rating, other.rating);
	}
	@Override
	public String toString() {
		return "RequestDto [rating=" + rating + ", promoted=" + promoted + "]";
	}
	

}
