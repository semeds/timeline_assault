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

        //left 45 slop
        Frame leftSlopeFrame = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftSlopeTile = new MapTileBuilder(leftSlopeFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createRight45SlopeLayout(spriteWidth, (int) tileScale));


        mapTiles.add(leftSlopeTile);

        //slop 45 right
        Frame rightSlopeFrame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder rightSlopeTile = new MapTileBuilder(rightSlopeFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createLeft45SlopeLayout(spriteWidth, (int) tileScale));


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

        //Road
        Frame Road1Frame = new FrameBuilder(getSubImage(1, 0))
         .withScale(tileScale)
         .build();
 
         MapTileBuilder Road1Tile = new MapTileBuilder(Road1Frame)
         .withTileType(TileType.NOT_PASSABLE); // Make this tile passable
         mapTiles.add(Road1Tile);

        //Broken road1
        Frame Road2Frame = new FrameBuilder(getSubImage(1, 1))
         .withScale(tileScale)
         .build();
 
         MapTileBuilder Road2Tile = new MapTileBuilder(Road2Frame)
         .withTileType(TileType.NOT_PASSABLE); // Make this tile passable
         mapTiles.add(Road2Tile);

        //Broken road2
        Frame Road3Frame = new FrameBuilder(getSubImage(1, 2))
         .withScale(tileScale)
         .build();
 
         MapTileBuilder Road3Tile = new MapTileBuilder(Road3Frame)
         .withTileType(TileType.NOT_PASSABLE); // Make this tile passable
         mapTiles.add(Road3Tile);

        // Tree tile (passable to act as background)
        Frame tree1Frame = new FrameBuilder(getSubImage(1, 3)) // 
        .withScale(tileScale)
        .build();

        MapTileBuilder tree1Tile = new MapTileBuilder(tree1Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(tree1Tile);

        //tree 2 mid
        Frame tree2Frame = new FrameBuilder(getSubImage(2, 3)) // 
        .withScale(tileScale)
        .build();

        MapTileBuilder tree2Tile = new MapTileBuilder(tree2Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(tree2Tile);

         //tree 3 bottom
         Frame tree3Frame = new FrameBuilder(getSubImage(3, 3)) 
         .withScale(tileScale)
         .build();
 
         MapTileBuilder tree3Tile = new MapTileBuilder(tree3Frame)
         .withTileType(TileType.PASSABLE);  // Make it passable to act as background
         mapTiles.add(tree3Tile);

         //tree 4 top
         Frame tree4Frame = new FrameBuilder(getSubImage(4, 3)) 
         .withScale(tileScale)
         .build();
 
         MapTileBuilder tree4Tile = new MapTileBuilder(tree4Frame)
         .withTileType(TileType.PASSABLE);  // Make it passable to act as background
         mapTiles.add(tree4Tile);

         //Tree 5 mid
         Frame tree5Frame = new FrameBuilder(getSubImage(4, 4)) 
         .withScale(tileScale)
         .build();
 
         MapTileBuilder tree5Tile = new MapTileBuilder(tree5Frame)
         .withTileType(TileType.PASSABLE);  // Make it passable to act as background
         mapTiles.add(tree5Tile);

        //Main sky block
        Frame YellowSkyFrame = new FrameBuilder(getSubImage(4, 2))
        .withScale(tileScale)
        .build();

        MapTileBuilder YellowSkyTile = new MapTileBuilder(YellowSkyFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(YellowSkyTile);

        //edgeBlocker
        Frame SkyedgeFrame = new FrameBuilder(getSubImage(3, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder SkyedgeTile = new MapTileBuilder(SkyedgeFrame)
                .withTileType(TileType.NOT_PASSABLE); // Make this tile not passable
        mapTiles.add(SkyedgeTile);


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

        //building 1top
        Frame Building1TopFrame = new FrameBuilder(getSubImage(1, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder Building1TopTile = new MapTileBuilder(Building1TopFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Building1TopTile);

        //building 1 mid
        Frame Building1MidFrame = new FrameBuilder(getSubImage(2, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder Building1MidTile = new MapTileBuilder(Building1MidFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Building1MidTile);

        //building 1 bottom
        Frame Building1BottomFrame = new FrameBuilder(getSubImage(3, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder Building1BottomTile = new MapTileBuilder(Building1BottomFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Building1BottomTile);

        //building 2 top
        Frame Building2topFrame = new FrameBuilder(getSubImage(1, 5))
        .withScale(tileScale)
        .build();

        MapTileBuilder Building2topTile = new MapTileBuilder(Building2topFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Building2topTile);

        //building 2 mid
        Frame Building2MidFrame = new FrameBuilder(getSubImage(2, 5))
        .withScale(tileScale)
        .build();

        MapTileBuilder Building2MidTile = new MapTileBuilder(Building2MidFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Building2MidTile);

        //building 2 bottom
        Frame Building2BottomFrame = new FrameBuilder(getSubImage(3, 5))
        .withScale(tileScale)
        .build();

        MapTileBuilder Building2BottomTile = new MapTileBuilder(Building2BottomFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Building2BottomTile);

        //building 3 top
        Frame Building3TopFrame = new FrameBuilder(getSubImage(3, 0))
        .withScale(tileScale)
        .build();

        MapTileBuilder Building3TopTile = new MapTileBuilder(Building3TopFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Building3TopTile);

        //building 3 bottom
        Frame Building3BottomFrame = new FrameBuilder(getSubImage(3, 1))
        .withScale(tileScale)
        .build();

        MapTileBuilder Building3BottomTile = new MapTileBuilder(Building3BottomFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Building3BottomTile);

         //left broken platform
         Frame leftBplatformFrame = new FrameBuilder(getSubImage(2, 0))
         .withScale(tileScale)
         .withBounds(0, 6, 16, 4)
         .build();

        MapTileBuilder leftBplatformTile = new MapTileBuilder(leftBplatformFrame)
         .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(leftBplatformTile);

        //middle broken platform
        Frame MiddleBplatformFrame = new FrameBuilder(getSubImage(2, 1))
         .withScale(tileScale)
         .withBounds(0, 6, 16, 4)
         .build();

        MapTileBuilder MiddleBplatformTile = new MapTileBuilder(MiddleBplatformFrame)
         .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(MiddleBplatformTile);

         //right broken platform
        Frame RightBplatformFrame = new FrameBuilder(getSubImage(2, 2))
         .withScale(tileScale)
         .withBounds(0, 6, 16, 4)
         .build();

         MapTileBuilder RightBplatformTile = new MapTileBuilder(RightBplatformFrame)
         .withTileType(TileType.JUMP_THROUGH_PLATFORM);

         mapTiles.add(RightBplatformTile);



//Map2 code:

         //Sky map2
         Frame BlueSkyFrame = new FrameBuilder(getSubImage(5, 0))
        .withScale(tileScale)
        .build();

        MapTileBuilder BlueSkyTile = new MapTileBuilder(BlueSkyFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(BlueSkyTile);

        //edge block
        Frame BlueSky1Frame = new FrameBuilder(getSubImage(9, 2))
        .withScale(tileScale)
        .build();

        MapTileBuilder BlueSky1Tile = new MapTileBuilder(BlueSky1Frame)
        .withTileType(TileType.NOT_PASSABLE); // Make this tile passable
        mapTiles.add(BlueSky1Tile);

        //Dirt Block m2
        Frame Dirt2Frame = new FrameBuilder(getSubImage(5, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder Dirt2Tile = new MapTileBuilder(Dirt2Frame)
                .withTileType(TileType.NOT_PASSABLE); // Make this tile not passable
        mapTiles.add(Dirt2Tile);

        //street block
        Frame RoadFrame = new FrameBuilder(getSubImage(5, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder RoadTile = new MapTileBuilder(RoadFrame)
                .withTileType(TileType.NOT_PASSABLE); // Make this tile not passable
        mapTiles.add(RoadTile);

        //Lamp head
        Frame LamplightFrame = new FrameBuilder(getSubImage(5, 3))  
        .withScale(tileScale)
        .build();

        MapTileBuilder LamplightTile = new MapTileBuilder(LamplightFrame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(LamplightTile);

        //lamp body
        Frame LampBodyFrame = new FrameBuilder(getSubImage(6, 3))  
        .withScale(tileScale)
        .build();

        MapTileBuilder LampBodyTile = new MapTileBuilder(LampBodyFrame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(LampBodyTile);

        //lamp bottom
        Frame LampBottomFrame = new FrameBuilder(getSubImage(7, 3))  
        .withScale(tileScale)
        .build();

        MapTileBuilder LampBottomTile = new MapTileBuilder(LampBottomFrame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(LampBottomTile);

        //city building 1
        Frame CityBuilding1Frame = new FrameBuilder(getSubImage(5, 4))  
        .withScale(tileScale)
        .build();

        MapTileBuilder CityBuilding1Tile = new MapTileBuilder(CityBuilding1Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(CityBuilding1Tile);

        //city building 2
        Frame CityBuilding2Frame = new FrameBuilder(getSubImage(5, 5))  
        .withScale(tileScale)
        .build();

        MapTileBuilder CityBuilding2Tile = new MapTileBuilder(CityBuilding2Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(CityBuilding2Tile);

        //city building 3
        Frame CityBuilding3Frame = new FrameBuilder(getSubImage(6, 4))  
        .withScale(tileScale)
        .build();

        MapTileBuilder CityBuilding3Tile = new MapTileBuilder(CityBuilding3Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(CityBuilding3Tile);

        //city building 4
        Frame CityBuilding4Frame = new FrameBuilder(getSubImage(6, 5))  
        .withScale(tileScale)
        .build();

        MapTileBuilder CityBuilding4Tile = new MapTileBuilder(CityBuilding4Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(CityBuilding4Tile);

        //house 1
        Frame House1Frame = new FrameBuilder(getSubImage(6, 0))  
        .withScale(tileScale)
        .build();

        MapTileBuilder House1Tile = new MapTileBuilder(House1Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(House1Tile);

        //house 1 p2
        Frame House2Frame = new FrameBuilder(getSubImage(7, 0))  
        .withScale(tileScale)
        .build();

        MapTileBuilder House2Tile = new MapTileBuilder(House2Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(House2Tile);

        //house 2 
        Frame House3Frame = new FrameBuilder(getSubImage(6, 1))  
        .withScale(tileScale)
        .build();

        MapTileBuilder House3Tile = new MapTileBuilder(House3Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(House3Tile);

        //house 2 p2
        Frame House4Frame = new FrameBuilder(getSubImage(7, 1))  
        .withScale(tileScale)
        .build();

        MapTileBuilder House4Tile = new MapTileBuilder(House4Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(House4Tile);

        //Firehydren top
        Frame Firehydren1Frame = new FrameBuilder(getSubImage(6, 2))  
        .withScale(tileScale)
        .build();

        MapTileBuilder Firehydren1Tile = new MapTileBuilder(Firehydren1Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(Firehydren1Tile);

        //Firehydren bottom
        Frame Firehydren2Frame = new FrameBuilder(getSubImage(7, 2))  
        .withScale(tileScale)
        .build();

        MapTileBuilder Firehydren2Tile = new MapTileBuilder(Firehydren2Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(Firehydren2Tile);

        //Tree top1

        Frame TreeTop1Frame = new FrameBuilder(getSubImage(7, 4))  
        .withScale(tileScale)
        .build();

        MapTileBuilder TreeTop1Tile = new MapTileBuilder(TreeTop1Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(TreeTop1Tile);

        //Tree top2

        Frame TreeTop2Frame = new FrameBuilder(getSubImage(7, 5))  
        .withScale(tileScale)
        .build();

        MapTileBuilder TreeTop2Tile = new MapTileBuilder(TreeTop2Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(TreeTop2Tile);

        //tree mid1
        Frame TreeMid1Frame = new FrameBuilder(getSubImage(8, 3))  
        .withScale(tileScale)
        .build();

        MapTileBuilder TreeTile = new MapTileBuilder(TreeMid1Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(TreeTile);

        //Tree mid2
        Frame Treemid2Frame = new FrameBuilder(getSubImage(8, 4))  
        .withScale(tileScale)
        .build();

        MapTileBuilder Treemid2Tile = new MapTileBuilder(Treemid2Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(Treemid2Tile);

        //Tree mid 3
        Frame Treemid3Frame = new FrameBuilder(getSubImage(8, 5))  
        .withScale(tileScale)
        .build();

        MapTileBuilder Treemid3Tile = new MapTileBuilder(Treemid3Frame)
        .withTileType(TileType.PASSABLE);  // Make it passable to act as background
        mapTiles.add(Treemid3Tile);

        //TreeBottom m2
        Frame TreeBottomm2Frame = new FrameBuilder(getSubImage(9, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder TreeBottomm2Tile = new MapTileBuilder(TreeBottomm2Frame)
                .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(TreeBottomm2Tile);

        //Left platform m2
        Frame Leftm2platformFrame = new FrameBuilder(getSubImage(8, 0))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder Leftm2platformTile = new MapTileBuilder(Leftm2platformFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(Leftm2platformTile);

        //Middle platform m2
        Frame Middlem2platformFrame = new FrameBuilder(getSubImage(8, 1))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder Middlem2platformTile = new MapTileBuilder(Middlem2platformFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(Middlem2platformTile);

        //Right platform m2
        Frame Rightm2platformFrame = new FrameBuilder(getSubImage(8, 2))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder Rightm2platformTile = new MapTileBuilder(Rightm2platformFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(Rightm2platformTile);

        //Cloud1 m2
        Frame Cloud1m2Frame = new FrameBuilder(getSubImage(9, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder Cloud1m2Tile = new MapTileBuilder(Cloud1m2Frame)
                .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Cloud1m2Tile);

        //Cloud2 m2
        Frame Cloud2m2Frame = new FrameBuilder(getSubImage(9, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder Cloud2m2Tile = new MapTileBuilder(Cloud2m2Frame)
                .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Cloud2m2Tile);

//Map 3 code:

        //Sky block
        Frame DarkBlueFrame = new FrameBuilder(getSubImage(10, 0))
        .withScale(tileScale)
        .build();

        MapTileBuilder DarkBlueTile = new MapTileBuilder(DarkBlueFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(DarkBlueTile);

        //Edge Block
        Frame DarkBlueEdgeFrame = new FrameBuilder(getSubImage(13, 3))
        .withScale(tileScale)
        .build();

        MapTileBuilder DarkBlueEdgeTile = new MapTileBuilder(DarkBlueEdgeFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(DarkBlueEdgeTile);

        //sky R and I
        Frame DarkStar1Frame = new FrameBuilder(getSubImage(14, 0))
        .withScale(tileScale)
        .build();

        MapTileBuilder DarkStar1Tile = new MapTileBuilder(DarkStar1Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(DarkStar1Tile);

        //sky F

        Frame DarkStar2Frame = new FrameBuilder(getSubImage(14, 1))
        .withScale(tileScale)
        .build();

        MapTileBuilder DarkStar2Tile = new MapTileBuilder(DarkStar2Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(DarkStar2Tile);

        //Sky T and !
        Frame DarkStar3Frame = new FrameBuilder(getSubImage(14, 2))
        .withScale(tileScale)
        .build();

        MapTileBuilder DarkStar3Tile = new MapTileBuilder(DarkStar3Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(DarkStar3Tile);

        //stars 
        Frame DarkStar4Frame = new FrameBuilder(getSubImage(14, 0))
        .withScale(tileScale)
        .build();

        MapTileBuilder DarkStar4Tile = new MapTileBuilder(DarkStar4Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(DarkStar4Tile);

        //moon 1
        Frame Moon1Frame = new FrameBuilder(getSubImage(13, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder Moon1Tile = new MapTileBuilder(Moon1Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Moon1Tile);

        //moon2
        Frame Moon2Frame = new FrameBuilder(getSubImage(13, 5))
        .withScale(tileScale)
        .build();

        MapTileBuilder Moon2Tile = new MapTileBuilder(Moon2Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Moon2Tile);

        //moon 3
        Frame Moon3Frame = new FrameBuilder(getSubImage(14, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder Moon3Tile = new MapTileBuilder(Moon3Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Moon3Tile);

        //Moon 4
        Frame Moon4Frame = new FrameBuilder(getSubImage(14, 5))
        .withScale(tileScale)
        .build();

        MapTileBuilder Moon4Tile = new MapTileBuilder(Moon4Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Moon4Tile);

        //ground block1
        Frame Darkground1Frame = new FrameBuilder(getSubImage(10, 1))
        .withScale(tileScale)
        .build();

        MapTileBuilder Darkground1Tile = new MapTileBuilder(Darkground1Frame)
        .withTileType(TileType.NOT_PASSABLE); // Make this tile not passable
        mapTiles.add(Darkground1Tile);

        //streetblock 
        Frame DarkStreet1Frame = new FrameBuilder(getSubImage(10, 2))
        .withScale(tileScale)
        .build();

        MapTileBuilder DarkStreet1Tile = new MapTileBuilder(DarkStreet1Frame)
        .withTileType(TileType.NOT_PASSABLE); // Make this tile passable
        mapTiles.add(DarkStreet1Tile);

        //lights
        Frame FuturelightFrame = new FrameBuilder(getSubImage(10, 3))
        .withScale(tileScale)
        .build();

        MapTileBuilder FuturelightTile = new MapTileBuilder(FuturelightFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(FuturelightTile);

        //light mid
        Frame Futurelight2Frame = new FrameBuilder(getSubImage(11, 3))
        .withScale(tileScale)
        .build();

        MapTileBuilder Futurelight2Tile = new MapTileBuilder(Futurelight2Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Futurelight2Tile);

        //light bottom
        Frame Futurelight3Frame = new FrameBuilder(getSubImage(12, 3))
        .withScale(tileScale)
        .build();

        MapTileBuilder Futurelight3Tile = new MapTileBuilder(Futurelight3Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Futurelight3Tile);

        //City build top 1
        Frame Citytop1Frame = new FrameBuilder(getSubImage(10, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder Citytop1Tile = new MapTileBuilder(Citytop1Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Citytop1Tile);

        //City Build top 2
        Frame Citytop2Frame = new FrameBuilder(getSubImage(10, 5))
        .withScale(tileScale)
        .build();

        MapTileBuilder Citytop2Tile = new MapTileBuilder(Citytop2Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(Citytop2Tile);

        //City build mid 1
        Frame CityMid1Frame = new FrameBuilder(getSubImage(11, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder CityMid1Tile = new MapTileBuilder(CityMid1Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(CityMid1Tile);

        //City build mid 2
        Frame CityMid2Frame = new FrameBuilder(getSubImage(11, 5))
        .withScale(tileScale)
        .build();

        MapTileBuilder CityMid2Tile = new MapTileBuilder(CityMid2Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(CityMid2Tile);

        //City build bottom 1
        Frame CityBottom1Frame = new FrameBuilder(getSubImage(12, 4))
        .withScale(tileScale)
        .build();

        MapTileBuilder CityBottom1Tile = new MapTileBuilder(CityBottom1Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(CityBottom1Tile);

        //City build bottom 2
        Frame CityBottom2Frame = new FrameBuilder(getSubImage(12, 5))
        .withScale(tileScale)
        .build();

        MapTileBuilder CityBottom2Tile = new MapTileBuilder(CityBottom2Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(CityBottom2Tile);

        //Shop 1 m3
        Frame CityHouse1Frame = new FrameBuilder(getSubImage(11, 0))
        .withScale(tileScale)
        .build();

        MapTileBuilder CityHouse1Tile = new MapTileBuilder(CityHouse1Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(CityHouse1Tile);
        
        //shop m3
        Frame CityHouse2Frame = new FrameBuilder(getSubImage(12, 0))
        .withScale(tileScale)
        .build();
        
        MapTileBuilder CityHouse2Tile = new MapTileBuilder(CityHouse2Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(CityHouse2Tile);

        //Shop m3
        Frame CityHouse3Frame = new FrameBuilder(getSubImage(11, 1))
        .withScale(tileScale)
        .build();

        MapTileBuilder CityHouse3Tile = new MapTileBuilder(CityHouse3Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(CityHouse3Tile);

        //Shop  m3
        Frame CityHouse4Frame = new FrameBuilder(getSubImage(12, 1))
        .withScale(tileScale)
        .build();

        MapTileBuilder CityHouse4Tile = new MapTileBuilder(CityHouse4Frame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(CityHouse4Tile);

        //screen hologram
        Frame HologramscreenFrame = new FrameBuilder(getSubImage(11, 2))
        .withScale(tileScale)
        .build();

        MapTileBuilder HologramscreenTile = new MapTileBuilder(HologramscreenFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(HologramscreenTile);
        
        //screen bottom
        Frame HologramBottomFrame = new FrameBuilder(getSubImage(12, 2))
        .withScale(tileScale)
        .build();

        MapTileBuilder HologramBottomTile = new MapTileBuilder(HologramBottomFrame)
        .withTileType(TileType.PASSABLE); // Make this tile passable
        mapTiles.add(HologramBottomTile);

        //left platform
        Frame Futureplatform1Frame = new FrameBuilder(getSubImage(13, 0))
         .withScale(tileScale)
         .withBounds(0, 6, 16, 4)
         .build();

        MapTileBuilder Futureplatform1Tile = new MapTileBuilder(Futureplatform1Frame)
         .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(Futureplatform1Tile);

        //mid platform
        Frame Futureplatform2Frame = new FrameBuilder(getSubImage(13, 1))
         .withScale(tileScale)
         .withBounds(0, 6, 16, 4)
         .build();

        MapTileBuilder Futureplatform2Tile = new MapTileBuilder(Futureplatform2Frame)
         .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(Futureplatform2Tile);

        //Right platform
        Frame Futureplatform3Frame = new FrameBuilder(getSubImage(13, 2))
         .withScale(tileScale)
         .withBounds(0, 6, 16, 4)
         .build();

        MapTileBuilder Futureplatform3Tile = new MapTileBuilder(Futureplatform3Frame)
         .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(Futureplatform3Tile);

        




        
        return mapTiles;
    }
}

