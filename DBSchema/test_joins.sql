select * from user 
join subscription ON user.id=subscription.user_id 
join subscription_has_publication ON subscription_has_publication.subscription_id=subscription.id
join publication ON subscription_has_publication.publication_id=publication.id where user.id=2publication