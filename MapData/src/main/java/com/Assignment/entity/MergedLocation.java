package com.Assignment.entity;

import lombok.Data;

@Data
public class MergedLocation {
	 private Location location;
	    private Metadata metadata;
	    
	    public MergedLocation(Location location, Metadata metadata) {
	        this.location = location;
	        this.metadata = metadata;
	    }

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		public Metadata getMetadata() {
			return metadata;
		}

		public void setMetadata(Metadata metadata) {
			this.metadata = metadata;
		}
	    
	    

}
