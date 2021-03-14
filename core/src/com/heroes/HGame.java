package com.heroes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.heroes.dto.CellMap;
import com.heroes.entity.CellEntity;
import com.heroes.enums.CellType;
import com.heroes.smartfont.SmartFontGenerator;
import com.heroes.utils.BattleUtils;
import com.heroes.utils.PointShapeRender;

import java.util.HashMap;
import java.util.Map;

public class HGame extends ApplicationAdapter {
	private static final float K_TOTAL = 0.65F;	// For 10 rows
	private static final float K_HOR  = 0.965F;
	private static final float K_VERT = 0.965F;
	private static final float SPACE_LEFT = 20F;
	private static final float SPACE_BOTTOM = 140F;
	private static final float SPACE_TOP = 80F;
	private static final int   ROW_NUM = 10;
	private static final int   COL_NUM = 12;
	private static final float VIEWPORT_WIDTH = 1920F;
	private static final float VIEWPORT_HEIGHT = 1080F;
	private static final float CENTER_X = VIEWPORT_WIDTH / 2F;
	private static final float BATTLE_HEIGHT_TOTAL = VIEWPORT_HEIGHT - SPACE_BOTTOM - SPACE_TOP;
	private static final float BATTLE_WIDTH_TOTAL = VIEWPORT_WIDTH - SPACE_LEFT * 2;
	private static final Map<Integer, Float> I_WIDTH_TOTAL = new HashMap();
	private static final Map<Integer, Float> I_WIDTH_CELL = new HashMap();
	private static final Map<Integer, Float> I_HEIGHT_CELL = new HashMap();
	private static final Map<Integer, Float> I_BOTTOM_CELL = new HashMap();
	static {
		float heightCellKTotal = 0F;
		float heightCellK = 1F;
		for (int i = 0; i < ROW_NUM; i++) {
			heightCellKTotal += heightCellK;
			heightCellK *= K_VERT;
		}
		float widthTotal = BATTLE_WIDTH_TOTAL;
		float bottomCell = SPACE_BOTTOM;
		float heightCell = BATTLE_HEIGHT_TOTAL / heightCellKTotal; //width * 0.36F;

		for (int i = 0; i < ROW_NUM + 1; i++) {
			I_WIDTH_TOTAL.put(i, widthTotal);
			widthTotal *= K_HOR;
		}
		for (int i = 0; i < ROW_NUM + 1; i++) {
			I_BOTTOM_CELL.put(i, bottomCell);
			I_HEIGHT_CELL.put(i, heightCell);
			bottomCell += heightCell;
			heightCell *= K_VERT;
		}
	}

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private BitmapFont font;
	private PointShapeRender shapeRenderer;
	private int rowNum = ROW_NUM, colNum = COL_NUM;
	private CellMap cellMap = new CellMap();
	{
		for (int row = 0; row < rowNum; row++) {
			float widthBot = I_WIDTH_TOTAL.get(row);
			float widthTop = I_WIDTH_TOTAL.get(row + 1);
			float x1BotLeft = CENTER_X - widthBot / 2F;
			float x1TopLeft = CENTER_X - widthTop / 2F;
			float widthBot1 = widthBot / colNum;
			float widthTop1 = widthTop / colNum;

			float yBottom = I_BOTTOM_CELL.get(row);
			float yTop = I_BOTTOM_CELL.get(row + 1);

			for (int col = 0; col < colNum; col++) {
				float x1Bot = x1BotLeft + widthBot1 * col;
				float x2Bot = x1BotLeft + widthBot1 * (col + 1);
				float x1Top = x1TopLeft + widthTop1 * col;
				float x2Top = x1TopLeft + widthTop1 * (col + 1);

				cellMap.put(row, col, new CellEntity(x1Bot, x2Bot, x1Top, x2Top, yBottom, yTop));
			}
		}
		cellMap.get(0, 0).cellTypeComponent.cellType = CellType.HIGHLIGHTED;
		cellMap.get(0, 1).cellTypeComponent.cellType = CellType.HIGHLIGHTED;
		cellMap.get(1, 0).cellTypeComponent.cellType = CellType.HIGHLIGHTED;
		cellMap.get(1, 1).cellTypeComponent.cellType = CellType.HIGHLIGHTED;
		cellMap.get(2, 0).cellTypeComponent.cellType = CellType.HIGHLIGHTED;
		cellMap.get(2, 1).cellTypeComponent.cellType = CellType.HIGHLIGHTED;
		cellMap.get(3, 0).cellTypeComponent.cellType = CellType.HIGHLIGHTED;
		cellMap.get(3, 1).cellTypeComponent.cellType = CellType.MOUSEOVER;
		cellMap.get(4, 0).cellTypeComponent.cellType = CellType.HIGHLIGHTED;
		cellMap.get(4, 1).cellTypeComponent.cellType = CellType.HIGHLIGHTED;
		cellMap.get(5, 0).cellTypeComponent.cellType = CellType.HIGHLIGHTED;
		cellMap.get(5, 1).cellTypeComponent.cellType = CellType.HIGHLIGHTED;
		cellMap.get(6, 0).cellTypeComponent.cellType = CellType.HIGHLIGHTED;
		cellMap.get(6, 1).cellTypeComponent.cellType = CellType.HIGHLIGHTED;
	}

	@Override
	public void create () {
		//MathUtils.random.setSeed(Long.MIN_VALUE);

		camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		camera.position.set(VIEWPORT_WIDTH / 2F, VIEWPORT_HEIGHT / 2F, 0F);
		camera.update();
		//font = new BitmapFont();
		batch = new SpriteBatch();
		SmartFontGenerator fontGenerator = new SmartFontGenerator();
		//fontGenerator.setForceGeneration(true);
		//font = fontGenerator.generateFontWriteFiles("devroyun", 24);
		//font = fontGenerator.loadFontFile("devroyun", 24);
		font = new BitmapFont(Gdx.files.internal("fnt/" + 24 + "_" + "devroyun.fnt"));
		shapeRenderer = new PointShapeRender();

	}

	@Override
	public void render () {
		camera.update();
		Gdx.gl.glClearColor(0.2F, 0.4F, 0.2F, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		batch.begin();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

		/*float yCurr = SPACE_BOTTOM;
		for (int i = 0; i < ROW_NUM + 1; i++) {
			float lengthTotal = I_WIDTH_TOTAL.get(i);
			float x1 = CENTER_X - lengthTotal / 2F;
			float x2 = CENTER_X + lengthTotal / 2F;
			float y1 = yCurr;
			float y2 = yCurr;
			yCurr += I_HEIGHT_CELL.get(i);
			shapeRenderer.line(x1, y1, x2, y2);
		}*/
		for (int row = 0; row < rowNum; row++) {
			for (int col = 0; col < colNum; col++) {
				CellEntity cellBot = row == 0 ? null : cellMap.get(row - 1, col);
				CellEntity cellLeft = col == 0 ? null : cellMap.get(row, col - 1);
				CellEntity cell = cellMap.get(row, col);
				Color colorBot = BattleUtils.selectColorForCurrAndOther(cell, cellBot);
				Color colorLeft = BattleUtils.selectColorForCurrAndOther(cell, cellLeft);
				/*shapeRenderer.polygon(new float[]{
						cell.leftBottom.x, cell.leftBottom.y,
						cell.leftTop.x, cell.leftTop.y,
						cell.rightTop.x, cell.rightTop.y,
						cell.rightBottom.x, cell.rightBottom.y
				});*/
				/*shapeRenderer.pol(cell.leftBottom.x, cell.leftBottom.y, 0F,
						cell.rightBottom.x - cell.leftBottom.x,
						cell.leftTop.y - cell.leftBottom.y,
						0F
				);*/
				shapeRenderer.line(cell.leftBottom, cell.rightBottom, colorBot);
				shapeRenderer.line(cell.leftBottom, cell.leftTop, colorLeft);
			}
		}
		for (int row = 0; row < rowNum; row++) {
			CellEntity cell = cellMap.get(row, colNum - 1);
			Color colorRight = BattleUtils.selectColorForCurr(cell);
			shapeRenderer.line(cell.rightBottom, cell.rightTop, colorRight);
		}
		for (int col = 0; col < colNum; col++) {
			CellEntity cell = cellMap.get(rowNum - 1, col);
			Color colorTop = BattleUtils.selectColorForCurr(cell);
			shapeRenderer.line(cell.leftTop, cell.rightTop, colorTop);
		}

		shapeRenderer.end();
		//font.draw(batch, "TekstТекст", 10, 300 - 10);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
