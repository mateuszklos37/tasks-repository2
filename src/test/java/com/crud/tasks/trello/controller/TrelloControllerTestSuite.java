package com.crud.tasks.trello.controller;

import com.crud.tasks.controller.TrelloController;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloControllerTestSuite {
    @InjectMocks
    private TrelloController trelloController;

    @Mock
    private TrelloFacade trelloFacade;

    @Test
    public void shouldGetTrelloBoards(){
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("test_board", "1", trelloLists));
        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloController.getTrelloBoards();
        //Then
        Assert.assertEquals(1, trelloBoardDtos.size());
        Assert.assertEquals("test_board", trelloBoardDtos.get(0).getName());
    }

    @Test
    public void shouldCreateNewCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test_name", "test_des", "top", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "Created card", "testUrl");
        when(trelloFacade.createCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto createdCard = trelloController.createTrelloCard(trelloCardDto);
        String name = createdCard.getName();
        String url = createdCard.getShortUrl();
        //Then
        Assert.assertEquals("Created card", name);
        Assert.assertEquals("testUrl", url);
    }
}
