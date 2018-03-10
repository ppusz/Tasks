package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloMapperTest.class);

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToCardDto() {
        // Given
        String taskName = "test task";
        String taskDescription = "test task description";
        String taskPosition = "top";
        String listId = "some id";
        TrelloCard card = new TrelloCard(taskName, taskDescription, taskPosition, listId);

        // When
        TrelloCardDto cardDto = trelloMapper.mapToCardDto(card);

        // Then
        LOGGER.info("Testing mapToCardDto method");
        Assert.assertEquals(taskName, cardDto.getName());
        Assert.assertEquals(taskDescription, cardDto.getDescription());
        Assert.assertEquals(taskPosition, cardDto.getPos());
        Assert.assertEquals(listId, cardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        String taskName = "test task";
        String taskDescription = "test task description";
        String taskPosition = "top";
        String listId = "some id";
        TrelloCardDto cardDto = new TrelloCardDto(taskName, taskDescription, taskPosition, listId);

        // When
        TrelloCard card = trelloMapper.mapToCard(cardDto);

        // Then
        LOGGER.info("Testing mapToCard method");
        Assert.assertEquals(taskName, card.getName());
        Assert.assertEquals(taskDescription, card.getDescription());
        Assert.assertEquals(taskPosition, card.getPos());
        Assert.assertEquals(listId, card.getListId());
    }

    @Test
    public void testMapToBoards() {
        // Given
        int boardsCount = 8;
        List<TrelloBoardDto> boardsDto = new ArrayList<>();
        for (int i = 1; i <= boardsCount; i++) {
            boardsDto.add(new TrelloBoardDto("board " + i, "name " + i, new ArrayList<TrelloListDto>() ));
        }

        // When
        List<TrelloBoard> boards = trelloMapper.mapToBoards(boardsDto);

        // Then
        LOGGER.info("Testing mapToBoards method");
        Assert.assertEquals(boardsCount, boards.size());
        for (int i = 1; i <= boardsCount; i++) {
            Assert.assertEquals("board " + i, boards.get(i - 1).getId());
            Assert.assertEquals("name " + i, boards.get(i - 1).getName());
            Assert.assertEquals(0, boards.get(i - 1).getLists().size());
        }
    }

    @Test
    public void testMapToBoardsDto() {
        // Given
        int boardsCount = 9;
        List<TrelloBoard> boards = new ArrayList<>();
        for (int i = 1; i <= boardsCount; i++) {
            boards.add(new TrelloBoard("board " + i, "name " + i, new ArrayList<TrelloList>() ));
        }

        // When
        List<TrelloBoardDto> boardsDto = trelloMapper.mapToBoardsDto(boards);

        // Then
        LOGGER.info("Testing mapToBoardsDto method");
        Assert.assertEquals(boardsCount, boardsDto.size());
        for (int i = 1; i <= boardsCount; i++) {
            Assert.assertEquals("board " + i, boardsDto.get(i - 1).getId());
            Assert.assertEquals("name " + i, boardsDto.get(i - 1).getName());
            Assert.assertEquals(0, boardsDto.get(i - 1).getLists().size());
        }
    }

    @Test
    public void testMapToList() {
        // Given
        String id = "list id";
        String name = "list name";
        int listSize = 10;
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        for (int i = 1; i <= listSize; i++) {
            trelloListsDto.add(new TrelloListDto(id + " " + i, name + " " + i, (i % 2 == 0) ? false : true));
        }

        // When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);

        // Then
        LOGGER.info("Testing mapToList method");
        Assert.assertEquals(listSize, trelloLists.size());
        Assert.assertFalse(trelloLists.get(1).isClosed());
        for (int i = 1; i <= listSize; i++) {
            Assert.assertEquals(id + " " + i, trelloLists.get(i - 1).getId());
            Assert.assertEquals(name + " " + i, trelloLists.get(i - 1).getName());
        }
    }

    @Test
    public void testMapToListDto() {
        // Given
        String id = "list id";
        String name = "list name";
        int listSize = 5;
        List<TrelloList> trelloLists = new ArrayList<>();
        for (int i = 1; i <= listSize; i++) {
            trelloLists.add(new TrelloList(id + " " + i, name + " " + i, (i % 2 == 0) ? false : true));
        }

        // When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);

        // Then
        LOGGER.info("Testing mapToListDto method");
        Assert.assertEquals(listSize, trelloListsDto.size());
        Assert.assertTrue(trelloListsDto.get(0).isClosed());
        for (int i = 1; i <= listSize; i++) {
            Assert.assertEquals(id + " " + i, trelloListsDto.get(i - 1).getId());
            Assert.assertEquals(name + " " + i, trelloListsDto.get(i - 1).getName());
        }
    }
}
