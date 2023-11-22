import { createRouter, createWebHistory } from 'vue-router'
import MainPageView from "@/views/MainPageView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: MainPageView
    },
    {
      path: '/edit',
      name: 'planEdit',
      component: () => import('@/views/PlanEditView.vue')
    },
    {
      path: '/plan',
      name: 'plan',
      component: () => import('@/views/PlanView.vue'),
      redirect: {name:'plan_list'},
      children: [
        {
          path : 'list',
          name : 'plan_list',
          component: () => import('@/components/Plan/PlanList.vue')
        },
        {
          path : 'detail/:plan_article_id',
          name : 'plan_detail',
          component: () => import('@/components/Plan/PlanDetail.vue')
        },
      ]
    },
    {
      path: '/notice',
      name: 'notice',
      component: () => import('@/views/NoticeBoardView.vue'),
      redirect: {name: "notice_list"},
      children: [
        {
          path : 'list',
          name : 'notice_list',
          component: () => import('@/components/Board/NoticeBoard/NoticeBoard.vue')
        },
        {
          path : 'detail/:noticeArticleId',
          name : 'notice_detail',
          component: () => import('@/components/Board/NoticeBoard/NoticeBoardDetail.vue')
        },
        {
          path : 'write',
          name : 'notice_article_write',
          component: () => import('@/components/Board/NoticeBoard/NoticeBoardWriteForm.vue')
        },
        {
          path : 'modify/:noticeArticleId',
          name : 'notice_article_modify',
          component: () => import('@/components/Board/NoticeBoard/NoticeBoardModifyForm.vue')
        },
      ]
    },
    {
      path: '/group',
      name: 'group',
      component: () => import('@/views/GroupView.vue')
    },
    {
      path: '/review',
      name: 'review',
      component: () => import('@/views/ReviewBoardView.vue')
    },
    {
      path: '/friend',
      name: 'friend',
      component: () => import('@/views/FriendView.vue')
    },
  ]
})

export default router
