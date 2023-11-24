<template>
    <div class="toast-edit" ref="editor"></div>
  </template>
  
  <script>
  import "prismjs/themes/prism.css";
  import "@toast-ui/editor-plugin-code-syntax-highlight/dist/toastui-editor-plugin-code-syntax-highlight.css";
  import "tui-color-picker/dist/tui-color-picker.css";
  import "@toast-ui/editor-plugin-color-syntax/dist/toastui-editor-plugin-color-syntax.css";
  import '@toast-ui/editor/dist/toastui-editor.css';
  
  import colorSyntax from "@toast-ui/editor-plugin-color-syntax";
  import Prism from "prismjs";
  import "prismjs/components/prism-c";
  import "prismjs/components/prism-cpp";
  import "prismjs/components/prism-java";
  import "prismjs/components/prism-python";
  import "@toast-ui/editor/dist/i18n/ko-kr";
  import Editor from "@toast-ui/editor";
  import codeSyntaxHighlight from "@toast-ui/editor-plugin-code-syntax-highlight";
  
  export default {
    components: {
    },
    props: {
      data: {
        type: String,
        default: "",
      },
  
    },
    data() {
      return {
        changedPrism: null,
        editor: null,
        editorText: ""
      };
    },
    mounted() {
      this.changedPrism = Prism;
  
      this.setEditor();
      if (this.data) {
        try {
          this.editor.setMarkdown(this.data);
        } catch (e) {
          this.editor.setHTML(this.data);
        }
      }
    },
    methods: {
      setEditor(data) {
        this.editor = new Editor({
          el: this.$refs.editor,
          plugins: [
            colorSyntax,
            [codeSyntaxHighlight, { highlighter: this.changedPrism }],
          ],
          initialEditType: "wysiwyg",
          language: "ko",
          events: {
            change: this.onChangeEditor,
          },
        //   hooks: {
        //     addImageBlobHook: this.addImageBlobHook,
        //   },
        });
      },
  
      async addImageBlobHook(file, setText, type) {
        try {
          if (!file) return false;
  
          // 이미지 제외 막기 처리하기
          if (file && file.size > 5242880) {
            const size = (file.size / (1000 * 1000)).toFixed(1);
            alert(
                `최대 업로드 사이즈(5 MB)를 초과 하였습니다.\n현재 사이즈 ${size}MB`
            );
  
            return false;
          }
          // api 업로드 만들기
          const formData = new FormData();
          formData.append("image", file);
          // const { data } = await api.uploadImageFile(formData);
          // if (data.success === true) {
          //   setText(data.file_path, "image");
          // }
  
          //
        } catch (e) {
          alert("파일 업로드에 실패하였습니다");
        }
      },
  
      onChangeEditor() {
        this.editorText = this.editor.getMarkdown();
  
      },
    },
    watch: {
      // watch를 활용한 props 변경 감지
      data(newValue) {
        if (this.editorText !== newValue) {
          this.editor.setMarkdown(newValue);
        }
      },
  
      editorText(newValue, oldValue) {
        if (newValue !== oldValue) {
          this.$emit("setContent", this.editor.getMarkdown());
        }
      },
    },
  };
  </script>
  
  <style>
  
  </style>