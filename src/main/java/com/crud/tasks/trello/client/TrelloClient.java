package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Component
public class TrelloClient {
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloAppToken;
    @Value("mateuszkos2")
    private String trelloAppUsername;

    @Autowired
    private RestTemplate restTemplate;

    private URI buildUrl(){
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/mateuszkos2/boards").
                queryParam("key", trelloAppKey).
                queryParam("token", trelloAppToken).
                queryParam("fields", "name,id").
                queryParam("lists", "all").build().encode().toUri();
    }

    public List<TrelloBoardDto> getTrelloBoards() {
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(buildUrl(), TrelloBoardDto[].class);
            return Arrays.asList(ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){
            URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards").
                    queryParam("key", trelloAppKey).
                    queryParam("token", trelloAppToken).
                    queryParam("name", trelloCardDto.getName()).
                    queryParam("desc", trelloCardDto.getDescription()).
                    queryParam("pos", trelloCardDto.getPos()).
                    queryParam("idList", trelloCardDto.getListId()).build().encode().toUri();
            return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }
}
