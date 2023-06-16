<template>
  <base-layout page-title="All Memories">
    <ion-item
      v-for="memory in memories"
      :router-link="`/memories/${memory.id}`"
      :key="memory.id"
    >
      <ion-thumbnail slot="start">
        <ion-img :src="memory.image" :alt="memory.title"></ion-img>
      </ion-thumbnail>
      <ion-label>
        {{ memory.title }}
      </ion-label>
    </ion-item>
  </base-layout>
</template>

<script>
import { defineComponent } from "vue";
import { IonList, IonItem, IonImg, IonThumbnail, IonLabel } from "@ionic/vue";

export default defineComponent({
  components: {
    IonList,
    IonItem,
    IonImg,
    IonThumbnail,
    IonLabel,
  },
  computed: {
    memories() {
      return this.$store.getters.memories;
    }
  }
});
</script>
