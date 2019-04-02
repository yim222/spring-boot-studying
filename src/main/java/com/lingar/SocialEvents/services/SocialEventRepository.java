package com.lingar.SocialEvents.services;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lingar.SocialEvents.entities.SocialEvent;

public interface SocialEventRepository extends PagingAndSortingRepository<SocialEvent, Long>{

}
