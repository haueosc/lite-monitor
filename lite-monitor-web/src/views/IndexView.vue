<template>
  <el-container class="main-container">
    <el-header class="main-header">
      <el-image style="height: 40px"
                :src="mainLogo"/>
      <div class="tabs">
        <table-item v-for="item in tabs" :name="item.name"
                    :active="item.id === tab" @click="changePage(item)"/>
        <div style="text-align: right;line-height: 16px;margin-right: 10px">
          <div>
            <el-tag type="success" v-if="store.isAdmin" size="small">管理员</el-tag>
            <el-tag v-else size="small">子账户</el-tag>
            {{store.user.username}}
          </div>
          <div style="font-size: 13px;color: grey">{{store.user.email}}</div>
        </div>
        <el-dropdown>
          <el-avatar class="el-avatar my-avatar"
                     :src="avatarLogo"/>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="userLogout">
                <el-icon><Back/></el-icon>
                LOG-OUT
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </el-header>
    <el-main class="main-content">
      <router-view v-slot="{ Component }">
        <transition name="el-fade-in-linear" mode="out-in">
          <keep-alive exclude="Security">
            <component :is="Component"/>
          </keep-alive>
        </transition>
      </router-view>
    </el-main>
  </el-container>
</template>

<script setup>
import {logout} from '@/net'
import router from "@/router";
import {Back} from "@element-plus/icons-vue";
import TableItem from "@/component/TableItem.vue";
import {useRoute} from "vue-router";
import {ref} from "vue";
import {useStore} from "@/store";
import mainLogo from '@/assets/logo/lite-monitor-nobg.png';
import avatarLogo from '@/assets/logo/lite-monitor-logo.png'

const store = useStore()
const route = useRoute()
const tabs = [
  {id: 1, name: '管理', route: 'manage'},
  // {id: 2, name: '报告', route: 'report'},
  {id: 3, name: '安全', route: 'security'}
]
const defaultIndex = () => {
  for (let tab of tabs) {
    if (route.name === tab.route)
      return tab.id
  }
  return 1
}
const tab = ref(defaultIndex())

function changePage(item) {
  tab.value = item.id;
  router.push({name: item.route})
}

function userLogout() {
  logout(() => router.push("/"))
}
</script>

<style scoped>

.main-container{

  height: 100vh;
  width: 100vw;
  .main-header{
    height: 55px;
    background-color: var(--el-bg-color);
    border-bottom: solid 1px var(--el-border-color);
    display: flex;
    align-items: center;

    .tabs{
      height: 55px;
      gap: 10px;
      flex: 1px;
      display: flex;
      align-items: center;
      justify-content: right;

      .my-avatar:focus-visible {
          outline: unset;
      }
      .my-avatar:hover{
        cursor: pointer;
        scale: 1.1;
        opacity: 0.8;
      }

    }
  }

  .main-content{
    height: 100%;

  }

  .main-content::before{
    content:'';
    position:absolute;
    top:0;
    left:0;
    width: 100%;
    height: 100%;
    background: #f5f5f5 url("@/assets/img/manage-background.jpg");
    filter:blur(2px);
    z-index:-1;
    background-size: cover;
  }
}

</style>
