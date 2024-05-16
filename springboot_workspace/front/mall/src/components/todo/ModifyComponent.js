import { useEffect, useState } from "react";
import { getOne, putOne, deleteOne } from "../../api/todoApi";
import useCustomMove from "../../hooks/useCustomMove";
import ResultModal from "../common/ResultModal";

const initState = {
  tno: 0,
  title: "",
  writer: "",
  dueDate: "null",
  complete: false,
};
const ModifyComponent = ({ tno, moveList, moveRead }) => {
  const [todo, setTodo] = useState({ ...initState });
  const [result, setResult] = useState(null);
  const { moveToList, moveToRead } = useCustomMove();

  useEffect(() => {
    getOne(tno).then((data) => setTodo(data));
  }, [tno]);
  const handleChangeTodo = (e) => {
    todo[e.target.name] = e.target.value;
    setTodo({ ...todo });
  };
  const handleChangeTodoComplete = (e) => {
    const value = e.target.value;
    todo.complete = value === "Y";
    setTodo({ ...todo });
  };
  const handleClickModify = () => {
    putOne(todo).then((result) => {
      console.log("modify result : " + result);
      setResult("Modified");
    });
  };
  const handleClickDelete = () => {
    deleteOne(tno).then((result) => {
      console.log("delete result : " + result);
      setResult("Deleted");
    });
  };
  const closeModal = () => {
    if (result == "Deleted") {
      moveToList();
    } else {
      moveToRead(tno);
    }
  };
  return (
    <div className="border-2 border-sky-200 mt-10 m-2 p-4">
      {result ? (
        <ResultModal
          title={"처리 결과"}
          content={result}
          callbackFn={closeModal}
        />
      ) : (
        <></>
      )}
      <div className="flex justify-center mt-10">
        <div className="relative mb-4 flex w-full flex-wrap items-stretch">
          <div className="w-1/5 p-6 text-right font-bold">TNO</div>

          <div className="w-4/5 p-6 rounded-r border border-solid shadow-md bg-gray-100">
            {todo.tno}
          </div>
        </div>{" "}
      </div>
      <div className="flex justify-center mt-10">
        <div className="relative mb-4 flex w-full flex-wrap items-stretch">
          <div className="w-1/5 p-6 text-right font-bold">WRITER</div>

          <div className="w-4/5 p-6 rounded-r border border-solid shadow-md bg-gray-100">
            {todo.writer}
          </div>
        </div>{" "}
      </div>
      <div className="flex justify-center">
        <div className="relative mb-4 flex w-full flex-wrap items-stretch">
          <div className="w-1/5 p-6 text-right font-bold">TITLE</div>
          <input
            className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md"
            name="title"
            type={"text"}
            value={todo.title}
            onChange={handleChangeTodo}
          ></input>
        </div>{" "}
      </div>
      <div className="flex justify-center">
        <div className="relative mb-4 flex w-full flex-wrap items-stretch">
          <div className="w-1/5 p-6 text-right font-bold">DUEDATE</div>
          <input
            className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md"
            name="dueDate"
            type={"date"}
            value={todo.dueDate}
            onChange={handleChangeTodo}
          ></input>
        </div>{" "}
      </div>
      <div className="flex justify-center">
        <div className="relative mb-4 flex w-full flex-wrap items-stretch">
          <div className="w-1/5 p-6 text-right font-bold">COMPLETE</div>
          <select
            className="border-solid border-2 rounded m-1 p-2"
            name="status"
            value={todo.complete ? "Y" : "N"}
            onChange={handleChangeTodoComplete}
          >
            <option value="Y">종료</option> <option value="N">진행중</option>{" "}
          </select>
        </div>
      </div>{" "}
      {/* 버튼 */}
      <div className="flex justify-end p-4">
        <button
          type="button"
          className="rounded p-4 m-2 bg-red-500 w-32 text-xl text-white"
          onClick={handleClickDelete}
        >
          Delete
        </button>

        <button
          type="button"
          className="rounded p-4 m-2 bg-blue-500 w-32 text-xl text-white"
          onClick={handleClickModify}
        >
          Modify
        </button>
      </div>
    </div>
  );
};
export default ModifyComponent;
