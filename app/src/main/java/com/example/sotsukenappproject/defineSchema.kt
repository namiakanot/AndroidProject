import androidx.room.*

/**
 * ユーザ情報を格納する
 */
@Entity(
    tableName = "userInfo",
    primaryKeys = ["user_id"]
)
data class UserEntity(
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "user_name")
    val userName: String,
    @ColumnInfo(name = "start_date")
    val startDate: Long,    // YYYY-MM-dd:HH24-mm-ss に直す。ミリ秒で格納。
    @ColumnInfo(name = "user_force")
    val userForce: Long
)

/**
 * 地方情報を格納する
 */
@Entity(
    tableName = "areaInfo",
    primaryKeys = ["area_id"]
)
data class AreaEntity(
    @ColumnInfo(name = "area_id")
    val areaId: Int,
    @ColumnInfo(name = "area_name")
    val areaName: String
)

/**
 * 都道府県情報を格納する
 */
@Entity(
    tableName = "prefInfo",
    primaryKeys = ["pref_id"],
    foreignKeys = [ForeignKey(
        entity = AreaEntity::class,
        parentColumns = arrayOf("area_id"),
        childColumns = arrayOf("area_id")
    )]
)
data class PrefEntity(
    @ColumnInfo(name = "pref_id")
    val prefId: Int,
    @ColumnInfo(name = "pref_name")
    val prefName: String,
    @ColumnInfo(name = "area_id")
    val areaId: Int,
    @ColumnInfo(name = "pref_force_1")
    val prefForce1: Long,
    @ColumnInfo(name = "pref_force_2")
    val prefForce2: Long
)

/**
 * 実績情報を格納する
 */
@Entity(
    tableName = "achievementInfo",
    primaryKeys = ["achievement_id"]
)
data class AchievementEntity(
    @ColumnInfo(name = "achievement_id")
    val achievementId: Int,
    @ColumnInfo(name = "achievement_name")
    val achievementName: String
)

/**
 * 育成情報を格納する
 */
@Entity(
    tableName = "campInfo",
    primaryKeys = ["camp_id"]
)
data class CampEntity(
    @ColumnInfo(name = "camp_id")
    val campId: Int,
    @ColumnInfo(name = "camp_name")
    val campName: String,
    @ColumnInfo(name = "need_time")
    val needTime: Long
)

/**
 * 進行履歴を格納する
 */
@Entity(
    tableName = "attackHistory",
    primaryKeys = ["user_id","attack_pref_id","execute_time"],
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = arrayOf("user_id"),
        childColumns = arrayOf("user_id"),
        onDelete = ForeignKey.CASCADE
    ),ForeignKey(
        entity = PrefEntity::class,
        parentColumns = arrayOf("pref_id"),
        childColumns = arrayOf("pref_id")
    )]
)
data class AttackHistoryEntity(
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "attack_pref_id")
    val attackPrefId: Int,
    @ColumnInfo(name = "execute_time")
    val executeTime: Long,
    @ColumnInfo(name = "spend_time")
    val spendTime: Long
)

/**
 * 育成履歴を格納する
 */
@Entity(
    tableName = "campHistory",
    primaryKeys = ["user_id","camp_id","execute_time"],
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = arrayOf("user_id"),
        childColumns = arrayOf("user_id"),
        onDelete = ForeignKey.CASCADE
    ),ForeignKey(
        entity = CampEntity::class,
        parentColumns = arrayOf("camp_id"),
        childColumns = arrayOf("camp_id")
    )]
)
data class CampHistoryEntity(
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "camp_id")
    val campId: Int,
    @ColumnInfo(name = "execute_time")
    val executeTime: Long,
    @ColumnInfo(name = "add_user_force")
    val addUserForce: Long
)

/**
 * 隣国判定の情報を格納する
 */
@Entity(
    tableName = "nextAttackAblePref",
    primaryKeys = ["attack_pref_id"],
    foreignKeys = [ForeignKey(
        entity = PrefEntity::class,
        parentColumns = arrayOf("pref_id"),
        childColumns = arrayOf("attacked_pref_id")
    ),ForeignKey(
        entity = PrefEntity::class,
        parentColumns = arrayOf("pref_id"),
        childColumns = arrayOf("next_attack_able_pref_id")
    )]
)
data class NextAttackAblePrefEntity(
    @ColumnInfo(name = "attacked_pref_id")
    val attackedPrefId: Int,
    @ColumnInfo(name = "next_attack_able_pref_id")
    val nextAttackAblePrefId: Int
)