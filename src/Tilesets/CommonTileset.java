package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;
import Utils.SlopeTileLayoutUtils;

import java.util.ArrayList;

// This class represents a new tileset based on your custom image
public class CommonTileset extends Tileset {

    public CommonTileset() {
        // Load your custom tileset image
        super(ImageLoader.load("CommonTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        //Dirt block
        Frame DirtFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder DirtTile = new MapTileBuilder(DirtFrame)
                .withTileType(TileType.NOT_PASSABLE); // Make this tile not passable
        mapTiles.add(DirtTile);

        // Grass
        Frame grassFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassTile = new MapTileBuilder(grassFrame)
                .withTileType(TileType.NOT_PASSABLE); // Make this tile not passable
        mapTiles.add(grassTile);

        //Red sky
        Frame RedSkyFrame = new FrameBuilder(getSubImage(2, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder RedSkyTile = new MapTileBuilder(RedSkyFrame)
                .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(RedSkyTile);

        //orang sky
        Frame OrangeSkyFrame = new FrameBuilder(getSubImage(2, 3))
        .withScale(tileScale)
        .build();

        MapTileBuilder OrangeSkyTile = new MapTileBuilder(OrangeSkyFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(OrangeSkyTile);


        //yellow sky
        Frame YellowSkyFrame = new FrameBuilder(getSubImage(2, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder YellowSkyTile = new MapTileBuilder(YellowSkyFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(YellowSkyTile);

        //cloud left
        Frame Cloud1SkyFrame = new FrameBuilder(getSubImage(4, 0))
        .withScale(tileScale)
        .build();

        MapTileBuilder Cloud1SkyTile = new MapTileBuilder(Cloud1SkyFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Cloud1SkyTile);

        //cloud right
        Frame Cloud2SkyFrame = new FrameBuilder(getSubImage(4, 1))
        .withScale(tileScale)
        .build();

        MapTileBuilder Cloud2SkyTile = new MapTileBuilder(Cloud2SkyFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Cloud2SkyTile);


        // City tile
        Frame cityFrame = new FrameBuilder(getSubImage(2, 1)) 
        .withScale(tileScale)
        .build();

        MapTileBuilder cityTile = new MapTileBuilder(cityFrame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(cityTile);

        //left 45 slop
        Frame leftSlopeFrame = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftSlopeTile = new MapTileBuilder(leftSlopeFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createLeft45SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(leftSlopeTile);

        //slop 45 right
        Frame rightSlopeFrame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder rightSlopeTile = new MapTileBuilder(rightSlopeFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createRight45SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(rightSlopeTile);
        

        //car tiles
        Frame CarbackFrame = new FrameBuilder(getSubImage(0, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder CarbackTile = new MapTileBuilder(CarbackFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(CarbackTile);


        //car front
        Frame CarfrontFrame = new FrameBuilder(getSubImage(0, 5))
        .withScale(tileScale)
        .build();

        MapTileBuilder CarfrontTile = new MapTileBuilder(CarfrontFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(CarfrontTile);


        // Tree tile (passable to act as background)
        Frame treeFrame = new FrameBuilder(getSubImage(1, 0)) // 
        .withScale(tileScale)
        .build();

        MapTileBuilder treeTile = new MapTileBuilder(treeFrame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(treeTile);

         //tree 2
         Frame tree2Frame = new FrameBuilder(getSubImage(3, 5)) 
         .withScale(tileScale)
         .build();
 
         MapTileBuilder tree2Tile = new MapTileBuilder(tree2Frame)
         .withTileType(TileType.PASSABLE);  // Make it passable to act as background
         mapTiles.add(tree2Tile);

        // city1 tile (passable to act as background)
        Frame city1Frame = new FrameBuilder(getSubImage(1, 1)) // 
        .withScale(tileScale)
        .build();

        MapTileBuilder city1Tile = new MapTileBuilder(city1Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(city1Tile);

        //city2
        Frame city2Frame = new FrameBuilder(getSubImage(1, 2))  
        .withScale(tileScale)
        .build();

        MapTileBuilder city2Tile = new MapTileBuilder(city2Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(city2Tile);

        //city 3
        Frame city3Frame = new FrameBuilder(getSubImage(1, 3)) 
        .withScale(tileScale)
        .build();

        MapTileBuilder city3Tile = new MapTileBuilder(city3Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(city3Tile);

        //brokensteet1 tile
        Frame Brokenstreet1Frame = new FrameBuilder(getSubImage(1, 4))  
        .withScale(tileScale)
        .build();

        MapTileBuilder Brokenstreet1Tile = new MapTileBuilder(Brokenstreet1Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(Brokenstreet1Tile);

        //brokestreet2 tile
        Frame Brokenstreet2Frame = new FrameBuilder(getSubImage(1, 5))  
        .withScale(tileScale)
        .build();

        MapTileBuilder Brokenstreet2Tile = new MapTileBuilder(Brokenstreet2Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(Brokenstreet2Tile);

        //brokensteet3 tile
        Frame Brokenstreet3Frame = new FrameBuilder(getSubImage(3, 3))  
        .withScale(tileScale)
        .build();

        MapTileBuilder Brokenstreet3Tile = new MapTileBuilder(Brokenstreet3Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(Brokenstreet3Tile);

        //brokestreet4 tile
        Frame Brokenstreet4Frame = new FrameBuilder(getSubImage(3, 4))  
        .withScale(tileScale)
        .build();

        MapTileBuilder Brokenstreet4Tile = new MapTileBuilder(Brokenstreet4Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(Brokenstreet4Tile);

        //left platform
        Frame leftplatformFrame = new FrameBuilder(getSubImage(2, 0))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder leftplatformTile = new MapTileBuilder(leftplatformFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(leftplatformTile);

        //middle platform
        Frame MiddleplatformFrame = new FrameBuilder(getSubImage(2, 1))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder MiddleplatformTile = new MapTileBuilder(MiddleplatformFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(MiddleplatformTile);

        //right platform
        Frame RightplatformFrame = new FrameBuilder(getSubImage(2, 2))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder RightplatformTile = new MapTileBuilder(RightplatformFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(RightplatformTile);


         //left broken platform
         Frame leftBplatformFrame = new FrameBuilder(getSubImage(3, 0))
         .withScale(tileScale)
         .withBounds(0, 6, 16, 4)
         .build();

        MapTileBuilder leftBplatformTile = new MapTileBuilder(leftBplatformFrame)
         .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(leftBplatformTile);

        //middle broken platform
        Frame MiddleBplatformFrame = new FrameBuilder(getSubImage(3, 1))
         .withScale(tileScale)
         .withBounds(0, 6, 16, 4)
         .build();

        MapTileBuilder MiddleBplatformTile = new MapTileBuilder(MiddleBplatformFrame)
         .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(MiddleBplatformTile);

         //right broken platform
        Frame RightBplatformFrame = new FrameBuilder(getSubImage(3, 2))
         .withScale(tileScale)
         .withBounds(0, 6, 16, 4)
         .build();

         MapTileBuilder RightBplatformTile = new MapTileBuilder(RightBplatformFrame)
         .withTileType(TileType.JUMP_THROUGH_PLATFORM);

         mapTiles.add(RightBplatformTile);



        return mapTiles;
    }
}
