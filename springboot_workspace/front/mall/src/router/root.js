// import { createBrowserRouter } from "react-router-dom";
import { Suspense, lazy } from "react"; // 필요한 순간까지 컴포넌트를 메모리상으로 올리지 않도록 지연로딩
import todoRouter from "./todoRouter";

const { createBrowserRouter } = require("react-router-dom");
const Loading = <div>Loading...</div>; // 컴포넌트의 처리가 끝나지 않은 경우 화면에 ‘Loading...’ 메시지 출력
const Main = lazy(() => import("../pages/MainPage"));
const About = lazy(() => import("../pages/AboutPage"));
const TodoIndex = lazy(() => import("../pages/todo/IndexPage"));
const TodoList = lazy(() => import("../pages/todo/ListPage"));

const root = createBrowserRouter([
  {
    path: "", // 경로가 '/' 혹은 아무것도 없을 때는 MainPage 컴포넌트를 보여줌
    element: (
      <Suspense fallback={Loading}>
        <Main />
      </Suspense>
    ), //지연로딩
  },
  {
    path: "about",
    element: (
      <Suspense fallback={Loading}>
        <About />
      </Suspense>
    ), //지연로딩
  },
  {
    path: "todo",
    element: (
      <Suspense fallback={Loading}>
        <TodoIndex />
      </Suspense>
    ), //지연로딩
    children: todoRouter(),
  },
]);
export default root;

// const root = createBrowserRouter([])

// export default root;
