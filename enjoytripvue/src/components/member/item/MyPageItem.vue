<script setup>
defineProps({ article: Object });
// const URL = article.mainImg;
const title = article.subject;
</script>

<template>
  <a-card hoverable :style="{ width: '250px', margin: '20px' }">
    <template #cover>
      <!-- <img :src="URL" /> -->
    </template>
    <a-card-meta :title="title">
      <template #description>{{ article.content }}</template>
    </a-card-meta>
  </a-card>
</template>

<style scoped></style>
