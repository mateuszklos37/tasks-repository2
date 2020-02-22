package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void testMappingCardToDto(){
        //Given
        TrelloCard trelloCard = new TrelloCard("Card1", "Description1", "top", "test");
        //When
        TrelloCardDto trelloCardDto =trelloMapper.mapToCardDto(trelloCard);
        String descriptionResult = trelloCardDto.getDescription();
        String posResult = trelloCardDto.getPos();
        //Then
        Assert.assertEquals("Description1", descriptionResult);
        Assert.assertEquals("top", posResult);
    }

    @Test
    public void testMappingCardDtoToDomain(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card2", "Description2", "top", "Test");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        String nameResult = trelloCard.getName();
        String testIdResult = trelloCard.getListId();
        //Then
        Assert.assertEquals("Card2", nameResult);
        Assert.assertEquals("Test", testIdResult);
    }

    @Test
    public void testMappingListToDto(){
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "name", false));
        trelloLists.add(new TrelloList("2", "name2", false));
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        int size = trelloListDtos.size();
        String nameResult = trelloListDtos.get(0).getName();
        //Then
        Assert.assertEquals(2, size);
        Assert.assertEquals("name", nameResult);
    }
    @Test
    public void testMappingListDtoToDomain(){
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "name1", false));
        trelloListDtos.add(new TrelloListDto("2", "name2", false));
        trelloListDtos.add(new TrelloListDto("3", "name3", false));
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToTrelloList(trelloListDtos);
        int size = trelloLists.size();
        String idResult = trelloLists.get(2).getId();
        //Then
        Assert.assertEquals(3, size);
        Assert.assertEquals("3", idResult);
    }
    @Test
    public void testBoardToDto(){
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "ToDo", true));
        trelloLists.add(new TrelloList("2", "InProgress", false));
        trelloBoards.add(new TrelloBoard("1", "Project1", trelloLists));
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);
        int listsOnBoard = trelloBoardDtos.get(0).getLists().size();
        String secondListOnBoardName = trelloBoardDtos.get(0).getLists().get(1).getName();
        //Assert
        Assert.assertEquals(2, listsOnBoard);
        Assert.assertEquals("InProgress", secondListOnBoardName);
    }
    @Test
    public void testMappingBoardDtoToDomain(){
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "ToDo", true));
        trelloListDtos.add(new TrelloListDto("2", "InProgress", false));
        trelloListDtos.add(new TrelloListDto("3", "Done", false));
        trelloBoardDtos.add(new TrelloBoardDto("REST Learning", "1", trelloListDtos));
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);
        String boardName = trelloBoards.get(0).getName();
        int howManyLists = trelloBoards.get(0).getLists().size();
        boolean isFirstListClosed = trelloBoards.get(0).getLists().get(0).isClosed();
        //Then
        Assert.assertEquals("REST Learning", boardName);
        Assert.assertEquals(3, howManyLists);
        Assert.assertTrue(isFirstListClosed);
    }
}
