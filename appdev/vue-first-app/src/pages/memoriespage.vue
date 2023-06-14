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
  data() {
    return {
      memories: [
        {
          id: "m1",
          image:
            "https://images.unsplash.com/photo-1545569341-9eb8b30979d9?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
          title: "Japan",
          description: "It was nice!",
        },
        {
          id: "m2",
          image:
            "https://images.unsplash.com/photo-1683009686716-ac2096a5a73b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
          title: "Hiking",
          description: "It was nice hiking!",
        },
        {
          id: "m3",
          image:
            "https://images.unsplash.com/photo-1470376619031-a6791e534bf0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
          title: "Swimming",
          description: "It was nice swimming!",
        },
      ],
    };
  },
});
</script>
