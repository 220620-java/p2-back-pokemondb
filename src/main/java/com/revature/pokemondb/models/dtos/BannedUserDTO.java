package com.revature.pokemondb.models.dtos;

import java.sql.Timestamp;
import java.time.Instant;

import com.revature.pokemondb.models.BannedUser;
import com.revature.pokemondb.models.User;

public class BannedUserDTO {
	private Long userId = 0l; 
	private Timestamp banDuration;
	private String banReason;

    public BannedUserDTO() {
        userId = 0l;
        banDuration = Timestamp.from(Instant.now());
        banReason = "";
    }

    public BannedUserDTO(Long userId) {
        this.userId = userId;
    }

    public BannedUserDTO(Long userId, Timestamp banDuration, String banReason) {
        this.userId = userId;
        this.banDuration = banDuration;
        this.banReason = banReason;
    }

    public BannedUserDTO(User user) {
        this.userId = user.getUserId();
    }

    public BannedUserDTO(BannedUser user) {
        this.userId = user.getUserId();
        this.banDuration = user.getBanDuration();
        this.banReason = user.getBanReason();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getBanDuration() {
        return banDuration;
    }

    public void setBanDuration(Timestamp banDuration) {
        this.banDuration = banDuration;
    }

    public String getBanReason() {
        return banReason;
    }

    public void setBanReason(String banReason) {
        this.banReason = banReason;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((banDuration == null) ? 0 : banDuration.hashCode());
        result = prime * result + ((banReason == null) ? 0 : banReason.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BannedUserDTO other = (BannedUserDTO) obj;
        if (banDuration == null) {
            if (other.banDuration != null)
                return false;
        } else if (!banDuration.equals(other.banDuration))
            return false;
        if (banReason == null) {
            if (other.banReason != null)
                return false;
        } else if (!banReason.equals(other.banReason))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }
}
