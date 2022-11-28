import androidx.room.*
import java.security.spec.MGF1ParameterSpec

/*  テーブル名:user
 *  用途:ユーザ名と開始日を登録する
 */

@Entity(
    tableName = "user", // テーブルの名前
    primaryKeys = ["userId"] // 主キー列の設定
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) // true -> userId列がAutoIncrementになる
    @ColumnInfo(name = "user_id") // 列名を設定
    val userId: Int,
    @ColumnInfo(name = "user_name")
    val userName: String,
    // yyyy-mm-dd で保存したい
    @ColumnInfo(name = "start_date")
    val startDate: String
)

/*  テーブル名:attackFlag
 *  用途:ユーザごとに進行したかどうかの確認フラグを設定する
 */

/* ForeignKey(参照キー,外部キー)の要素
 * entity -> 参照するクラスを指定
 * parentColumns -> 参照する列名を指定
 * childColumns -> 参照元の中で参照先の列と同じ値を持つ列を指定
 * onDelete -> 参照先が削除されたときの挙動
 */
@Entity(
    tableName = "attackedFlag",
    primaryKeys = ["user_id","pref_id"],
    foreignKeys =
    [ForeignKey(
        entity = UserEntity::class, // 参照先親クラス名::class
        parentColumns = arrayOf("userId"), // arrayOf("参照する列名")
        childColumns = arrayOf("userId"), // arrayOf("参照先と同じ値を持つ子アクテビティの列名")
        onDelete = ForeignKey.CASCADE // 参照先の値が削除された場合の子アクテビティの挙動
        // Foreign.CASCADE -> 参照先が削除されると、子の同値行も削除される
    ),
    ForeignKey(
        entity = PrefectureEntity::class,
        parentColumns = arrayOf("pref_id"),
        childColumns = arrayOf("pref_id")
    )]
)
data class AttackedFlagEntity(
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "pref_id")
    val prefId: Int,
    // 1周目,2周目とかをカウントする(上限2)
    @ColumnInfo(name = "attacked_count")
    val attackedCount: Int
)

/*  テーブル名:prefInfo
 *  用途:各国の情報を保持
 */

@Entity(
    tableName = "prefInfo",
    primaryKeys = ["pref_id"]
)
data class PrefectureEntity(
    @ColumnInfo(name = "pref_id")
    val prefId: Int,
    @ColumnInfo(name = "pref_name")
    val prefName: String,
    @ColumnInfo(name = "pref_force_track1")
    val prefForce1: Long,
    @ColumnInfo(name = "pref_force_track2")
    val prefForce2: Long
)