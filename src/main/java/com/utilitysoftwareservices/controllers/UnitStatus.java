package com.utilitysoftwareservices.controllers;

/**
 * Status of unit on the table top
 * 
 */
enum UnitStatus {
    /**
     * the unit is empty, so available for occupying
     * 
     */
    EMPTY,
    /**
     * the unit is occupied by a blocker
     * 
     */
    BLOCKED,
    /**
     * the unit is occupied by an explorer
     * 
     */
    EXPLORER_ON
}