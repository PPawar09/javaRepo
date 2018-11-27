package com.websystique.springmvc.domain;

import lombok.Data;

import java.net.URL;

@Data
public class Picture {
    private URL large;
    private URL medium;
    private URL thumbnail;
	public URL getLarge() {
		return large;
	}
	public void setLarge(URL large) {
		this.large = large;
	}
	public URL getMedium() {
		return medium;
	}
	public void setMedium(URL medium) {
		this.medium = medium;
	}
	public URL getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(URL thumbnail) {
		this.thumbnail = thumbnail;
	}
    
    
}
