package com.betr.server.game.domain;

public enum NFLPosition {
	TE,
	FB,
	WR,
	QB,
	RB,
	G,
	OLB,
	ILB,
	MLB,
	LG,
	C,
	RG,
	LT,
	RT,
	DB,
	SS,
	DL,
	DT,
	NT,
	DE,
	CB,
	OT,
	T,
	LB,
	LS,
	FS,
	P,
	K,
	DEF;
	
	public boolean isPlayableType() {
		if(this == TE || this == WR || this == QB || this == RB || this == K || this == DEF) {
			return true;
		}
		return false;
	}
}
