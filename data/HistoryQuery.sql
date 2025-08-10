-- 作品详情
SELECT
    w.title,
    w.short_desc,
    w.image_url,
    w.created_at,
    w.work_type,
    COUNT(DISTINCT l.user_id) as like_count,
    COUNT(DISTINCT c.comment_id) as comment_count
FROM
    works w
    LEFT JOIN likes l ON w.work_id = l.work_id
    LEFT JOIN comments c ON w.work_id = c.work_id
WHERE
    w.user_id = (
        SELECT userid
        FROM user
        WHERE
            username = 'rong'
    )
    and w.work_type = 'image_title_desc'
GROUP BY
    w.work_id,
    w.title,
    w.short_desc,
    w.image_url,
    w.created_at,
    w.work_type;

-- 文章详情
SELECT
    w.title,
    w.short_desc,
    w.created_at,
    w.work_type,
    COUNT(DISTINCT l.user_id) as like_count,
    COUNT(DISTINCT c.comment_id) as comment_count
FROM
    works w
    LEFT JOIN likes l ON w.work_id = l.work_id
    LEFT JOIN comments c ON w.work_id = c.work_id
WHERE
    w.user_id = (
        SELECT userid
        FROM user
        WHERE
            username = 'rong'
    )
    and w.work_type = 'title_desc'
GROUP BY
    w.work_id,
    w.title,
    w.short_desc,
    w.created_at,
    w.work_type;

-- 图片详情
select
    image_url as imageUrl,
    created_at as createTime
from works as w
where
    w.user_id = (
        SELECT userid
        FROM user
        WHERE
            username = 'rong'
    )
    and work_type = 'image_only';

-- 查询用户所有好友
-- 查询用户rong的所有好友详细信息 --- 参数：username='rong'
-- f1作为当前用户，f2作为好友
select friend.*
from
    follows f1,
    follows f2,
    (
        select userid
        from user
        where
            username = 'rong'
    ) currentUser,
    user friend
where
    f1.follower_id = f2.following_id
    and f1.following_id = f2.follower_id
    and currentUser.userid = f1.follower_id
    and friend.userid = f2.follower_id
    and currentUser.userid != friend.userid;

/* SELECT
friend.userid as friend_id,
friend.username as friend_name,
friend.avatar_url as friend_avatar,
friend.personal_description as friend_desc,
friend.address as friend_address,
friend.create_time as friend_join_time,
f1.created_at as became_friends_time
FROM
follows f1,
follows f2,
(
select *
from user
where
username = 'rong'
) currentUser,
(
select *
from user, f2
where
user.userid = f2.following_id
) friend
WHERE
f1.follower_id = f2.following_id
AND f1.following_id = f2.follower_id
AND currentUser.username = 'rong'
AND (
f1.follower_id = currentUser.userid
AND f1.following_id = friend.userid
)
AND friend.userid != currentUser.userid
ORDER BY f1.created_at DESC; */