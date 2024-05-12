import React from "react";
import Comment from "./Comment";

const comments = [
    {
        name: "J.Choi",
        comment: "My first component",
    },
    {
        name: "Kyle Lee",
        comment: "His first component",
    },
    {
        name: "Rachel Kim",
        comment: "Her first component",
    }
]
function CommentList(props){
    return(
        <div>
            {comments.map((comment) => {
                return <Comment name={comment.name} comment={comment.comment}/>;
            })}
            {/* <Comment name={"J.Choi"} comment={"My first component"}/>
            <Comment name={"Billy Lee"} comment={"My first component"}/> */}
        </div>
    )
}

export default CommentList;