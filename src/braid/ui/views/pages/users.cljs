(ns braid.ui.views.pages.users
  (:require [chat.client.reagent-adapter :refer [subscribe]]
            [braid.ui.views.group-invite :refer [group-invite-view]]
            [braid.ui.views.pills :refer [user-pill-view]]))

(defn user-list-view
  [users]
  [:ul
   (doall
     (for [user users]
       ^{:key (user :id)}
       [:li [user-pill-view (user :id)]]))])

(defn users-page-view
  []
  (let [online-users (subscribe [:users-in-open-group :online])
        offline-users (subscribe [:users-in-open-group :offline])]
    (fn []
      [:div.page.users
       [:div.title "Users"]
       [:div.content
        [:div.description
         [:h2 "Online"]
         [user-list-view @online-users]

         [:h2 "Offline"]
         [user-list-view @offline-users]]

        [:h2 "Invite"]
        [group-invite-view]]])))