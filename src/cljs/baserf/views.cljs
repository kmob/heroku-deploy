(ns baserf.views
  (:require
   [re-frame.core :as re-frame]
   [baserf.subs :as subs]
   ))


;; home
(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 (str "Hello from " @name ". This is the NEW Home Page.")]

     [:div
      [:ul
       [:li [:a {:href "#/test"} "go to Test Page"]]
       [:li [:a {:href "#/about"} "go to About Page"]]]
      ]
     ]))

;; about
(defn test-panel []
  [:div
   [:h1 "This is the Test Page again."]

   [:div
    [:a {:href "#/"}
     "go to Home Page"]]])

;; about
(defn about-panel []
  [:div
   [:h1 "This is the About Page with updates."]

   [:div
    [:a {:href "#/"}
     "go to Home Page"]]])


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :test-panel [test-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))
