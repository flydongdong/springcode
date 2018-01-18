package com.fly.dd.mycode.entity.game;

import java.io.Serializable;

/**
 * 基本人物
 *
 * Created by zhuyd on 2017/3/31.
 */
public class BasePerson implements Serializable {
    private static final long serialVersionUID = -4099340134132192324L;

    private long id;

    private String name;

    private int age;

    private int maxPower;

    private int maxIt;

    /**
     * 最大HP
     */
    private long maxHP;

    /**
     * 最大MP
     */
    private long maxMP;

    /**
     * 经验
     */
    private long exp;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    public int getMaxIt() {
        return maxIt;
    }

    public void setMaxIt(int maxIt) {
        this.maxIt = maxIt;
    }

    public long getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(long maxHP) {
        this.maxHP = maxHP;
    }

    public long getMaxMP() {
        return maxMP;
    }

    public void setMaxMP(long maxMP) {
        this.maxMP = maxMP;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }
}
