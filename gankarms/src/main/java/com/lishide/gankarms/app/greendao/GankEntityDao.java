package com.lishide.gankarms.app.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lishide.gankarms.mvp.model.entity.GankEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GANK_ENTITY".
*/
public class GankEntityDao extends AbstractDao<GankEntity, String> {

    public static final String TABLENAME = "GANK_ENTITY";

    /**
     * Properties of entity GankEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property CreatedAt = new Property(1, String.class, "createdAt", false, "CREATED_AT");
        public final static Property Desc = new Property(2, String.class, "desc", false, "DESC");
        public final static Property PublishedAt = new Property(3, String.class, "publishedAt", false, "PUBLISHED_AT");
        public final static Property Source = new Property(4, String.class, "source", false, "SOURCE");
        public final static Property Type = new Property(5, String.class, "type", false, "TYPE");
        public final static Property Url = new Property(6, String.class, "url", false, "URL");
        public final static Property Used = new Property(7, boolean.class, "used", false, "USED");
        public final static Property Who = new Property(8, String.class, "who", false, "WHO");
    }


    public GankEntityDao(DaoConfig config) {
        super(config);
    }
    
    public GankEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GANK_ENTITY\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"CREATED_AT\" TEXT," + // 1: createdAt
                "\"DESC\" TEXT," + // 2: desc
                "\"PUBLISHED_AT\" TEXT," + // 3: publishedAt
                "\"SOURCE\" TEXT," + // 4: source
                "\"TYPE\" TEXT," + // 5: type
                "\"URL\" TEXT," + // 6: url
                "\"USED\" INTEGER NOT NULL ," + // 7: used
                "\"WHO\" TEXT);"); // 8: who
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GANK_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GankEntity entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String createdAt = entity.getCreatedAt();
        if (createdAt != null) {
            stmt.bindString(2, createdAt);
        }
 
        String desc = entity.getDesc();
        if (desc != null) {
            stmt.bindString(3, desc);
        }
 
        String publishedAt = entity.getPublishedAt();
        if (publishedAt != null) {
            stmt.bindString(4, publishedAt);
        }
 
        String source = entity.getSource();
        if (source != null) {
            stmt.bindString(5, source);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(6, type);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(7, url);
        }
        stmt.bindLong(8, entity.getUsed() ? 1L: 0L);
 
        String who = entity.getWho();
        if (who != null) {
            stmt.bindString(9, who);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GankEntity entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String createdAt = entity.getCreatedAt();
        if (createdAt != null) {
            stmt.bindString(2, createdAt);
        }
 
        String desc = entity.getDesc();
        if (desc != null) {
            stmt.bindString(3, desc);
        }
 
        String publishedAt = entity.getPublishedAt();
        if (publishedAt != null) {
            stmt.bindString(4, publishedAt);
        }
 
        String source = entity.getSource();
        if (source != null) {
            stmt.bindString(5, source);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(6, type);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(7, url);
        }
        stmt.bindLong(8, entity.getUsed() ? 1L: 0L);
 
        String who = entity.getWho();
        if (who != null) {
            stmt.bindString(9, who);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public GankEntity readEntity(Cursor cursor, int offset) {
        GankEntity entity = new GankEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // createdAt
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // desc
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // publishedAt
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // source
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // type
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // url
            cursor.getShort(offset + 7) != 0, // used
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // who
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GankEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setCreatedAt(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDesc(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPublishedAt(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSource(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setType(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUrl(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setUsed(cursor.getShort(offset + 7) != 0);
        entity.setWho(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final String updateKeyAfterInsert(GankEntity entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(GankEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(GankEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}