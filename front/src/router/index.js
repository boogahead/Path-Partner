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
      path: '/search',
      name: 'search',
      component: () => import('@/views/SearchView.vue')
    },
    {
      path: '/plan',
      name: 'plan',
      component: MainPageView
    },
    {
      path: '/board',
      name: 'board',
      component: () => import('@/views/BoardView.vue'),
      redirect: {name:'qna'},
      children: [
        {
          path : 'notice',
          name : 'notice',
          component: () => import('@/components/Board/NoticeBoard.vue')
        },
        {
          path : 'qna',
          name : 'qna',
          component: () => import('@/components/Board/QnABoard.vue')
        },
        {
          path : 'detail/:articleno',
          name : 'detail',
          component: () => import('@/components/Board/BoardDetail.vue')
        },
        {
          path : 'write',
          name : 'article-write',
          component: () => import('@/components/Board/BoardWriteForm.vue')
        },
        {
          path : 'modify/:articleno',
          name : 'article-modify',
          component: () => import('@/components/Board/BoardModifyForm.vue')
        },
      ]
    },
    {
      path: '/group',
      name: 'group',
      component: MainPageView
    },
    {
      path: '/friend',
      name: 'friend',
      component: MainPageView
    },
  ]
})

export default router
