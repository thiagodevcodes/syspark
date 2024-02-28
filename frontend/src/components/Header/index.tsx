import styles from "./header.module.css"
import { ReactElement } from "react" 
import Nav from "../Nav"
import Image from "next/image"
import { useState, useEffect } from "react"
import MobileButton from "../MobileButton"
import Aside from "../Sidebar"

interface HeaderProps {
    children?: React.ReactNode;
}


export default function Header({ children }: HeaderProps): ReactElement {
    const [width, setWidth] = useState<number>(0)

    useEffect(() => {
        const handleResize = () => {
          setWidth(window.innerWidth);
        };
    
        if (typeof window !== 'undefined') {
          window.addEventListener('resize', handleResize);
    
          setWidth(window.innerWidth);
    
          return () => {
            window.removeEventListener('resize', handleResize);
          };
        }
    }, [])
    
    return (
        <header className={styles.header}>
            <div className={styles.flexCenter}>
                    {children}
                    { width > 800 ?
                    <> 
                        <Image src={"/img/Parking.svg"} width={50} height={50} alt="Logo"></Image>
                        <h2>SysPark</h2>     
                    </> : ""
                    }
            </div>
 
            <Nav/>
        </header>
    )
}